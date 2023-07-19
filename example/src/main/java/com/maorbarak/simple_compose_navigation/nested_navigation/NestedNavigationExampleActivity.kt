package com.maorbarak.simple_compose_navigation.nested_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maorbarak.simple_compose_navigation.nested_navigation.components.BottomNavigationItem
import com.maorbarak.simple_compose_navigation.ui.theme.SimpleComposeNavigationTheme

class NestedNavigationExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController: NavHostController = rememberNavController()

            SimpleComposeNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(1f)) {
                            ParentNavigationImpl.navHost(navController)
                        }
                        ParentBottomNavigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ParentBottomNavigation(navController: NavHostController) {
    val navControllerStackState = navController.currentBackStackEntryAsState()

    Card(
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavigationItem(
                "ChildA",
                navControllerStackState.value?.destination?.route == ParentNavigationDirections.ParentA.route,
                ParentNavigationDirections.ParentA,
                navController
            )

            BottomNavigationItem(
                "ChildB",
                navControllerStackState.value?.destination?.route == ParentNavigationDirections.ParentB.route,
                ParentNavigationDirections.ParentB,
                navController
            )
        }
    }
}