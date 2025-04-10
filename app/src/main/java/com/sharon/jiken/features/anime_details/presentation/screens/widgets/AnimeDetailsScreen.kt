package com.sharon.jiken.features.anime_details.presentation.screens.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sharon.jiken.features.anime_details.presentation.AnimeDetailsViewModel
import com.sharon.jiken.features.anime_details.presentation.intents.AnimeDetailsIntent
import com.sharon.jiken.features.main.presentation.screens.widgets.RoundedCornerImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    navController: NavController,
    viewModel: AnimeDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    LaunchedEffect(
        key1 = Unit, block = {
            viewModel.onIntent(AnimeDetailsIntent.GetAdminDetails(animeId))
        })

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Anime details") }, navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.popBackStack()
                            })
                    )

                },


                modifier = Modifier.fillMaxWidth()
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {


            if (state?.animeList?.trailer?.youtubeId != null) MiniVideoPlayer(
                videoUrl = state?.animeList?.trailer?.youtubeId ?: ""
            ) else RoundedCornerImage(state?.animeList?.images?.jpg?.largeImageUrl ?: "")

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "${state?.animeList?.title}",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Synopsis",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("${state?.animeList?.synopsis}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Genres",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow {
                items(state?.animeList?.genres?.size ?: 0) { index ->
                    val item = state?.animeList?.genres?.get(index)
                    AssistChip(
                        label = { Text("${item?.name} ") },
                        modifier = Modifier.padding(end = 8.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.ThumbUp,
                                contentDescription = "",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        },
                        onClick = {},

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Episodes: ${state?.animeList?.episodes}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Rating: ${state?.animeList?.rating}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )


        }

    }

}