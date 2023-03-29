package at.htl.remplbauer.music.ui.songs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.data.Song
import at.htl.remplbauer.music.model.ArtistRepository
import at.htl.remplbauer.music.model.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val songsRepository: SongRepository
) : ViewModel() {
    var songs: List<Song> by mutableStateOf((emptyList()))

    init {
        viewModelScope.launch {
            songs = getSongs()

        }
    }

    private suspend fun getSongs(): List<Song> {
        return songsRepository.getSongs()
    }
}