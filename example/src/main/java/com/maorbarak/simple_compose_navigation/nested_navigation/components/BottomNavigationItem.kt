package com.maorbarak.simple_compose_navigation.nested_navigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.maorbarak.simple_compose_navigation.navigation.NavigationDirections

@Composable
fun RowScope.BottomNavigationItem(
    text: String,
    isActive: Boolean,
    direction: NavigationDirections,
    navController: NavHostController
) {
    Box(modifier = Modifier.weight(1f).clickable {
        navController.navigate(direction.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }) {
        Text(
            text = text,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.fillMaxSize().padding(all = 16.dp),
            textAlign = TextAlign.Center
        )
    }
}