package com.sharon.jiken.features.main.presentation.screens.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sharon.jiken.features.main.domain.models.Data


@Composable
fun AnimeListItem(
    animeModelItem: Data?, onAnimeClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                onClick = {
                    animeModelItem?.malId?.let {
                        onAnimeClick(it)
                    }
                })) {

        RoundedCornerImage(image = animeModelItem?.images?.jpg?.largeImageUrl ?: "")
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = animeModelItem?.title ?: "", style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Episodes: " + animeModelItem?.episodes.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Text(
            text = animeModelItem?.rating.toString(), style = MaterialTheme.typography.bodySmall
        )
    }
}