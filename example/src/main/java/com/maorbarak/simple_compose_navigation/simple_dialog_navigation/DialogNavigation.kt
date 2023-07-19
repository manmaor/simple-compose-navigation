package com.maorbarak.simple_compose_navigation.simple_dialog_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route

private const val SCREEN_A_ROUTE = "ScreenA"
private const val MY_DIALOG_ROUTE = "MyDialog"

@Navigation(firstRoute = SCREEN_A_ROUTE)
interface DialogNavigation {

    @Route(SCREEN_A_ROUTE)
    fun NavGraphBuilder.screenA() = composable(SCREEN_A_ROUTE) { ScreenA() }

    @Route(MY_DIALOG_ROUTE)
    fun NavGraphBuilder.myDialog() = dialog(MY_DIALOG_ROUTE) { MyDialog() }
}