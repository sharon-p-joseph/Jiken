package com.sharon.jiken.features.main.presentation.screens

import AnimeListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sharon.jiken.features.anime_details.presentation.screens.widgets.AnimeDetailsScreen
import com.sharon.jiken.ui.theme.JikenTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

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
                    navController = navController, startDestination = AnimeListRoute
                ) {
                    composable<AnimeListRoute> {
                        AnimeListScreen { animeId ->
                            navController.navigate(AnimeDetailsRoute(animeId))
                        }
                    }

                    composable<AnimeDetailsRoute>

                    { backStackEntry ->
                        val animeId: AnimeDetailsRoute = backStackEntry.toRoute()

                        AnimeDetailsScreen(animeId = animeId.id, navController = navController)
                    }
                }
            }
        }
    }
}


@Serializable
object AnimeListRoute

@Serializable
class AnimeDetailsRoute(val id: Int)







