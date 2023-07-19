package com.maorbarak.simple_compose_navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maorbarak.simple_compose_navigation.nested_navigation.NestedNavigationExampleActivity
import com.maorbarak.simple_compose_navigation.simple_dialog_navigation.SimpleDialogNavigationExample
import com.maorbarak.simple_compose_navigation.simple_navigation.SimpleNavigationExampleActivity
import com.maorbarak.simple_compose_navigation.ui.theme.SimpleComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, SimpleNavigationExampleActivity::class.java))
                        }) {
                            Text(text = "Simple Navigation Example")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, SimpleDialogNavigationExample::class.java))
                        }) {
                            Text(text = "Simple Dialog Example")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, NestedNavigationExampleActivity::class.java))
                        }) {
                            Text(text = "Nested Navigation Example")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleComposeNavigationTheme {
        Greeting("Android")
    }
}