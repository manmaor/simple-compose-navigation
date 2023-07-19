package com.maorbarak.simple_compose_navigation.simple_dialog_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.maorbarak.simple_compose_navigation.simple_navigation.SimpleNavigationImpl.screenA
//import com.maorbarak.simple_compose_navigation.simple_navigation.SimpleNavigationImpl.screenB
import com.maorbarak.simple_compose_navigation.ui.theme.SimpleComposeNavigationTheme

//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost

class SimpleDialogNavigationExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController: NavHostController = rememberNavController()

            SimpleComposeNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DialogNavigationImpl.navHost(navController)
                }
            }
        }
    }
}

