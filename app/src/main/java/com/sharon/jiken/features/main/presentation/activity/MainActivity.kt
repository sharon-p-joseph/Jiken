package com.sharon.jiken.features.main.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sharon.jiken.R
import com.sharon.jiken.features.main.domain.models.Data
import com.sharon.jiken.features.main.presentation.AnimeViewModel
import com.sharon.jiken.features.main.presentation.intents.AnimeListIntents
import com.sharon.jiken.ui.theme.JikenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: AnimeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JikenTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Top anime")
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) { innerPadding ->
                    AnimeList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        viewModel.onIntent(AnimeListIntents.GetAnimeList)
    }

}


@Composable
fun AnimeListItem(
    animeModelItem: Data?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    animeModelItem?.images?.jpg?.largeImageUrl
                )
                .crossfade(true)
                .build(),
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            onError = { error ->
                Log.e("ImageLoadingError", "Reason: ${error.result.throwable}")
            },
            modifier = Modifier
                .clipToBounds()
                .height(180.dp)
                .fillMaxWidth()
                .layoutId("imageSection")
                .clip(RoundedCornerShape(8.dp))
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = animeModelItem?.title ?: "",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Episodes: " + animeModelItem?.episodes.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Text(
            text = animeModelItem?.rating.toString(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
fun AnimeList(modifier: Modifier, viewModel: AnimeViewModel = hiltViewModel()) {
    val state by viewModel.state.observeAsState()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {


        items(state?.animeList?.size ?: 0) { index ->
            val item = state?.animeList?.get(index)
            AnimeListItem(animeModelItem = item)
        }
    }
}