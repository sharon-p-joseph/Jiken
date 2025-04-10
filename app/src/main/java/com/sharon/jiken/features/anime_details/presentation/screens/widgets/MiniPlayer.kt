package com.sharon.jiken.features.anime_details.presentation.screens.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.SimpleYouTubePlayerOptionsBuilder
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayer
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerHostState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubeVideoId
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MiniVideoPlayer(
    videoUrl: String,
) {
    val coroutineScope = rememberCoroutineScope()
    val hostState = remember { YouTubePlayerHostState() }

    when (val state = hostState.currentState) {
        is YouTubePlayerState.Error -> {
            Text(text = "Error: ${state.message}")
        }

        YouTubePlayerState.Idle -> {
        }

        is YouTubePlayerState.Playing -> {
        }

        YouTubePlayerState.Ready -> coroutineScope.launch {
            hostState.loadVideo(YouTubeVideoId(videoUrl))
        }
    }

    YouTubePlayer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        hostState = hostState,
        options = SimpleYouTubePlayerOptionsBuilder.builder {
            autoplay(true)
            controls(false)
            rel(false)
            ivLoadPolicy(false)
            ccLoadPolicy(false)
            fullscreen = true
        },
    )

}
