package com.maorbarak.simple_compose_navigation.processor

import com.squareup.kotlinpoet.ClassName

fun getTypeAliasNavController(packageName: String, simpleName: String) =
    ClassName(packageName, simpleName + "NavController")

fun getDirectionsClassName(packageName: String, simpleName: String) =
    ClassName(packageName, simpleName + "Directions")


object ComposeClassNames {
    val navHostController = ClassName("androidx.navigation", "NavHostController")
    val composable = ClassName("androidx.compose.runtime", "Composable")
    val providableCompositionLocal = ClassName("androidx.compose.runtime", "ProvidableCompositionLocal")
    val staticCompositionLocalOf = ClassName("androidx.compose.runtime", "staticCompositionLocalOf")
    val compositionLocalProvider = ClassName("androidx.compose.runtime", "CompositionLocalProvider")
    val navHost = ClassName("androidx.navigation.compose", "NavHost")
    val navController = ClassName("androidx.navigation", "NavController")
}