package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.writeTo

class NavigationSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols: Sequence<KSClassDeclaration> = resolver
            .getSymbolsWithAnnotation(Navigation::class.java.name)
            .filterIsInstance<KSClassDeclaration>()

        if (symbols.iterator().hasNext().not()) return emptyList()

        symbols.forEach { symbol ->
            when {
                symbol.classKind != ClassKind.INTERFACE -> {
                    logger.error("should be interface", symbol)
                    return emptyList()
                }
                symbol.isCompanionObject -> {
                    logger.error("should not be companion object", symbol)
                    return emptyList()
                }
            }

            val firstRoute = symbol.annotations
                .getAnnotation(Navigation::class.java.simpleName)
                .arguments.getParameterValue<String>(Navigation.firstRoute)

            val declaredFunctions = symbol.getDeclaredFunctions()
                .mapNotNull { it.annotations.getAnnotationIfExist(Route::class.java.simpleName)?.arguments?.getParameterValue<String>(Route.name) }

            if (!declaredFunctions.contains(firstRoute)) {
                logger.error("doesn't contains route named `$firstRoute` in [${declaredFunctions.joinToString { it }}]", symbol)
            }


            handleNavigationSymbol(symbol, firstRoute)
        }

        return symbols.filterNot { it.validate() }.toList()
    }

    fun handleNavigationSymbol(classDeclaration: KSClassDeclaration, firstRoute: String) {
        val packageName = classDeclaration.packageName.asString()
        val classSimpleName = classDeclaration.simpleName.asString()

        val fileName = "${classSimpleName}Impl"
        val fileSpec = FileSpec.builder(packageName, fileName)

        // TypeAlias and Navigate extension fun
        classDeclaration.accept(TypeAliasAndExtensionFunctionVisitor(logger, fileSpec), Unit)

        // Directions
        classDeclaration.accept(NavigationDirectionsVisitor(logger, fileSpec), Unit)

        // Navigation
        classDeclaration.accept(NavigationImplVisitor(logger, fileSpec, firstRoute), Unit)

        fileSpec.build().writeTo(codeGenerator, Dependencies(false, classDeclaration.containingFile!!))
    }
}