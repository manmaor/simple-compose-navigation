package com.maorbarak.simple_compose_navigation.nested_navigation.child_a

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route

private const val CHILD_AA_ROUTE = "ChildAA"
private const val CHILD_AB_ROUTE = "ChildAB"

@Navigation(CHILD_AA_ROUTE)
interface ChildANavigation {

    @Route(CHILD_AA_ROUTE)
    fun NavGraphBuilder.childAA() = composable(CHILD_AA_ROUTE) { Box(Modifier.fillMaxSize()) { Text(text = "ChildAA", modifier = Modifier.align(Alignment.Center)) } }

    @Route(CHILD_AB_ROUTE)
    fun NavGraphBuilder.childAB() = composable(CHILD_AB_ROUTE) { Box(Modifier.fillMaxSize()) { Text(text = "ChildAB", modifier = Modifier.align(Alignment.Center)) } }
}