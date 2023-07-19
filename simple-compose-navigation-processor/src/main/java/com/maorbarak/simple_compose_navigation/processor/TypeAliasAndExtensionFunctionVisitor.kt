package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeAliasSpec

class TypeAliasAndExtensionFunctionVisitor(
    private val logger: KSPLogger,
    private val fileSpec: FileSpec.Builder,
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {

        val packageName = classDeclaration.packageName.asString()
        val className = classDeclaration.simpleName.asString()

        val destinationsClassName = getDirectionsClassName(packageName, className)
        val typeAliasNavControllerClassName = getTypeAliasNavController(packageName, className)

        fileSpec.addTypeAlias(TypeAliasSpec.builder(typeAliasNavControllerClassName.simpleName, ComposeClassNames.navController).build())
        fileSpec.addFunction(
            FunSpec.builder("navigate")
                .receiver(typeAliasNavControllerClassName)
                .addParameter("direction", destinationsClassName)
                .addStatement("this.navigate(direction.route)")
                .build()
        )
    }
}