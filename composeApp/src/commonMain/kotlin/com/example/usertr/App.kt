package com.example.usertr

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.usertr.navigation.AppNavigation

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        AppNavigation(
            navController = navController
        )
    }
}