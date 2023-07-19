package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.maorbarak.simple_compose_navigation.annotations.Route
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class NavigationImplVisitor(
    private val logger: KSPLogger,
    private val fileSpec: FileSpec.Builder,
    private val firstRoute: String,
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {

        val packageName = classDeclaration.packageName.asString()
        val className = classDeclaration.simpleName.asString()

        val originalInterfaceClassName = ClassName(packageName, className)
        val navigationImplClassName = ClassName(packageName, className + NAVIGATION_SUFFIX)
        val typeAliasNavControllerClassName = getTypeAliasNavController(packageName, className)

        val localNavControllerProperty: PropertySpec
        val navHostControllerParameter = ParameterSpec("navController", ComposeClassNames.navHostController)

        val navigationImpl = TypeSpec.objectBuilder(navigationImplClassName).addSuperinterface(originalInterfaceClassName)
            // private localNavController
            .addProperty(
                PropertySpec
                    .builder("localNavController", ComposeClassNames.providableCompositionLocal.parameterizedBy(typeAliasNavControllerClassName.copy(nullable = true)), KModifier.PRIVATE)
                    .initializer("%T { null }", ComposeClassNames.staticCompositionLocalOf.parameterizedBy(typeAliasNavControllerClassName.copy(nullable = true)))
                    .build()
                    .apply { localNavControllerProperty = this }
            )
            // @Composable fun navHost(navController)
            .addFunction(
                FunSpec
                    .builder("navHost")
                    .addAnnotation(ComposeClassNames.composable)
                    .addParameter(navHostControllerParameter)
                    .beginControlFlow("%T(%N provides %N)", ComposeClassNames.compositionLocalProvider, localNavControllerProperty, navHostControllerParameter)
                    .beginControlFlow("%T(navController = navController, startDestination = %S)", ComposeClassNames.navHost, firstRoute)
                    .apply {
                        classDeclaration.getDeclaredFunctions()
                            .filter { it.annotations.hasAnnotation(Route::class.java.simpleName) }
                            .forEach { addStatement("$it()") }
                    }
                    .endControlFlow()
                    .endControlFlow()
                    .build()
            )
            // val current: CustomNavigationNavController?
            .addProperty(
                PropertySpec
                    .builder("current", typeAliasNavControllerClassName.copy(nullable = true))
                    .getter(
                        FunSpec
                            .getterBuilder()
                            .addAnnotation(ComposeClassNames.composable)
                            .addStatement("return %N.current", localNavControllerProperty)
                            .build()
                    )
                    .build()
            )
            .build()

        fileSpec.addType(navigationImpl)
    }

    companion object {
        private const val NAVIGATION_SUFFIX = "Impl"
    }
}