package com.edu.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.edu.cinemaapp.models.FilmModel
import com.edu.cinemaapp.ui.theme.CinemaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    CinemaAppTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                val viewModel = viewModel<HomeViewModel>()
                val state by viewModel.uiState.collectAsState()

                LaunchedEffect(state) {
                    Log.d("MainActivity", "LaunchedEffect: $state")
                }

                HomeScreen(
                    state = state,
                    onFilmClicked = { film ->
                        navController.navigate(route = film)
                    },
                    onRefresh = { viewModel.load() },
                )
            }
            composable<FilmModel> { backStackEntry ->
                DetailsScreen(
                    film = backStackEntry.toRoute(),
                    popBack = { navController.popBackStack() },
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CinemaAppTheme {
        Greeting("Android Custom")
    }
}