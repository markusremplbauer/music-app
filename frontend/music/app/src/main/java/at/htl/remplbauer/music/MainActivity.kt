package at.htl.remplbauer.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import at.htl.remplbauer.music.ui.artists.ArtistsListScreen
import at.htl.remplbauer.music.ui.artists.ArtistsViewModel
import at.htl.remplbauer.music.ui.artists.LikedArtistsScreen
import at.htl.remplbauer.music.ui.songs.SongListScreen
import at.htl.remplbauer.music.ui.songs.SongsViewModel
import at.htl.remplbauer.music.ui.theme.BooksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val artistVM: ArtistsViewModel by viewModels()
    private val songVM: SongsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksTheme {
                Tabs(artistVM, songVM)
            }
        }
    }
}


@Composable
fun Tabs(artistsVM: ArtistsViewModel, songsVM: SongsViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf("Songs", "Artists", "Liked Artists")
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        when (tabIndex) {
            0 -> SongListScreen(viewModel = songsVM)
            1 -> ArtistsListScreen(viewModel = artistsVM)
            2 -> LikedArtistsScreen(viewModel = artistsVM)
        }
    }
}