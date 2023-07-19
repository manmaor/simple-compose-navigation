package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.*
import com.maorbarak.simple_compose_navigation.navigation.NavigationDirections
import com.maorbarak.simple_compose_navigation.annotations.Route
import com.squareup.kotlinpoet.*

class NavigationDirectionsVisitor(
    private val logger: KSPLogger,
    private val fileSpec: FileSpec.Builder
) : KSVisitorVoid() {

    private lateinit var classBuilder: TypeSpec.Builder
    private lateinit var directionsClassName: ClassName

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {

        directionsClassName = getDirectionsClassName(classDeclaration.packageName.asString(), classDeclaration.simpleName.asString())

        classBuilder = TypeSpec
            .classBuilder(directionsClassName)
            .addModifiers(KModifier.SEALED)
            .addSuperinterface(NavigationDirections::class)

        classDeclaration
            .getDeclaredFunctions()
            .forEach { it.accept(this, data) }

        fileSpec.addType(classBuilder.build())
    }

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {

        if (!function.annotations.hasAnnotation(Route::class.java.simpleName))
            return

        if (function.extensionReceiver.toString() != "NavGraphBuilder") {
            logger.error("must extend NavGraphBuilder. e.g fun NavGraphBuilder.$function() = ...", function)
        }

        val routeName = function.annotations
            .getAnnotation(Route::class.java.simpleName)
            .arguments.getParameterValue<String>(Route.name)

        classBuilder.addType(
            TypeSpec
                .objectBuilder(routeName)
                .superclass(directionsClassName)
                .addProperty(
                    PropertySpec
                        .builder(NavigationDirections.ROUTE_PROPERTY_NAME, String::class, KModifier.OVERRIDE)
                        .initializer("%S", routeName)
                        .build()
                )
                .build()
        )
    }
}