package com.maorbarak.simple_compose_navigation.nested_navigation.child_b

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
fun ChildBScreen() {
    val navController: NavHostController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            ChildBNavigationImpl.navHost(navController)
        }
        ChildBBottomNavigation(navController)
    }
}

@Composable
fun ChildBBottomNavigation(navController: NavHostController) {
    val navControllerStackState = navController.currentBackStackEntryAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavigationItem(
            "ChildBA",
            navControllerStackState.value?.destination?.route == ChildBNavigationDirections.ChildBA.route,
            ChildBNavigationDirections.ChildBA,
            navController
        )

        BottomNavigationItem(
            "ChildBB",
            navControllerStackState.value?.destination?.route == ChildBNavigationDirections.ChildBB.route,
            ChildBNavigationDirections.ChildBB,
            navController
        )
    }
}