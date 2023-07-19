package com.maorbarak.simple_compose_navigation.simple_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenB() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Screen B",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}