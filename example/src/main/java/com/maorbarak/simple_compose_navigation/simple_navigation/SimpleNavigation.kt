package com.maorbarak.simple_compose_navigation.simple_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route

private const val SCREEN_A_ROUTE = "ScreenA"
private const val SCREEN_B_ROUTE = "ScreenB"

@Navigation(firstRoute = "ScreenA")
interface SimpleNavigation {

    @Route(SCREEN_A_ROUTE)
    fun NavGraphBuilder.screenA() = composable(SCREEN_A_ROUTE) { ScreenA() }

    @Route(SCREEN_B_ROUTE)
    fun NavGraphBuilder.screenB() = composable(SCREEN_B_ROUTE) { ScreenB() }
}