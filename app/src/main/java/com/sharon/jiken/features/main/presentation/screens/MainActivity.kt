package com.sharon.jiken.features.main.presentation.screens

import AnimeListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sharon.jiken.features.anime_details.presentation.screens.widgets.AnimeDetailsScreen
import com.sharon.jiken.ui.theme.JikenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JikenTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController, startDestination = "anime_list"
                ) {
                    composable("anime_list") {
                        AnimeListScreen { animeId ->
                            navController.navigate("anime_details/$animeId")
                        }
                    }

                    composable(
                        route = "anime_details/{animeId}", arguments = listOf(
                            navArgument("animeId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val animeId = backStackEntry.arguments?.getInt("animeId") ?: -1

                        AnimeDetailsScreen(animeId = animeId, navController = navController)
                    }
                }
            }
        }
    }
}







