package com.sharon.jiken.features.main.presentation.screens.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sharon.jiken.features.main.presentation.AnimeViewModel
import com.sharon.jiken.features.main.presentation.intents.AnimeListIntents
import com.sharon.jiken.features.main.presentation.state.AnimeListStatus

@Composable
fun AnimeList(
    modifier: Modifier, onAnimeClick: (Int) -> Unit, viewModel: AnimeViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()

    LaunchedEffect(
        key1 = Unit, block = {
            viewModel.onIntent(AnimeListIntents.GetAnimeList())
        })

    when (state?.status) {
        AnimeListStatus.LOADING -> Column(modifier=Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
        AnimeListStatus.SUCCESS -> LazyColumn(
            modifier = modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)
        ) {


            items(state?.animeList?.size ?: 0) { index ->
                val item = state?.animeList?.get(index)
                AnimeListItem(animeModelItem = item, onAnimeClick = {
                    onAnimeClick(state?.animeList?.get(index)?.malId ?: -1)
                })
            }
        }
        else -> Box(modifier = Modifier.fillMaxWidth())
    }
}