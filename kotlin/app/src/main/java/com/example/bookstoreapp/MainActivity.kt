package com.example.bookstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookstoreapp.ui.theme.BookStoreAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("registration") { RegistrationScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("addBook") { AddBookScreen(navController) }
                    composable("editBook/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        if (id != null) {
                            EditBookScreen(navController, id)
                        }
                    }
                }
            }
        }
    }
}
