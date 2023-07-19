package com.maorbarak.simple_compose_navigation.nested_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route
import com.maorbarak.simple_compose_navigation.nested_navigation.child_a.ChildAScreen
import com.maorbarak.simple_compose_navigation.nested_navigation.child_b.ChildBScreen

private const val PARENT_A_ROUTE = "ParentA"
private const val PARENT_B_ROUTE = "ParentB"

@Navigation(PARENT_A_ROUTE)
interface ParentNavigation {

    @Route(PARENT_A_ROUTE)
    fun NavGraphBuilder.parentA() = composable(PARENT_A_ROUTE) { ChildAScreen() }

    @Route(PARENT_B_ROUTE)
    fun NavGraphBuilder.parentB() = composable(PARENT_B_ROUTE) { ChildBScreen() }
}