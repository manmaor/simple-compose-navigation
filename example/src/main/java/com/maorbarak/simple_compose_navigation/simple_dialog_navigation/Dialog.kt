package com.maorbarak.simple_compose_navigation.simple_dialog_navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MyDialog() {
    val navController = DialogNavigationImpl.current

    Dialog(onDismissRequest = { navController?.navigateUp() }) {
        MyDialogBody()
    }
}

@Composable
fun MyDialogBody() {
    val navController = DialogNavigationImpl.current

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .width(250.dp)
            .height(150.dp),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "My Dialog")
            Button(onClick = { navController?.navigateUp() }) {
                Text(text = "Close")
            }
        }
    }
}