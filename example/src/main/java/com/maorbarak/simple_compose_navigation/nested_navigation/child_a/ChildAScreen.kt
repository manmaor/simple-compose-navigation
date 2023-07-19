package com.maorbarak.simple_compose_navigation.nested_navigation.child_a

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maorbarak.simple_compose_navigation.nested_navigation.components.BottomNavigationItem

@Composable
fun ChildAScreen() {
    val navController: NavHostController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            ChildANavigationImpl.navHost(navController)
        }
        ChildABottomNavigation(navController)
    }
}

@Composable
fun ChildABottomNavigation(navController: NavHostController) {
    val navControllerStackState = navController.currentBackStackEntryAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavigationItem(
            "ChildAA",
            navControllerStackState.value?.destination?.route == ChildANavigationDirections.ChildAA.route,
            ChildANavigationDirections.ChildAA,
            navController
        )

        BottomNavigationItem(
            "ChildAB",
            navControllerStackState.value?.destination?.route == ChildANavigationDirections.ChildAB.route,
            ChildANavigationDirections.ChildAB,
            navController
        )
    }
}