import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sharon.jiken.features.main.presentation.screens.widgets.AnimeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    onAnimeClick: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Anime List") },

                modifier = Modifier.fillMaxWidth()
            )
        },
    ) { innerPadding ->

        AnimeList(modifier = Modifier.padding(innerPadding), onAnimeClick = {
            onAnimeClick(it)
        })
    }


}