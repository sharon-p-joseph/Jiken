package com.sharon.jiken.features.main.presentation.screens.widgets

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sharon.jiken.R

@Composable
fun RoundedCornerImage(image: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(
            image
        ).crossfade(true).build(),
        contentDescription = "image",
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

}