package at.htl.remplbauer.music.ui.artists

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.model.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val artistRepository: ArtistRepository
): ViewModel() {
    val authorsState: MutableState<List<Artist>> = mutableStateOf((emptyList()))

    init {
        viewModelScope.launch {
            val artists = getArtists()
            authorsState.value = artists
        }
    }


    private suspend fun getArtists():List<Artist> {
        return artistRepository.getArtists()
    }


    fun addArtist(artist: Artist) = viewModelScope.launch(Dispatchers.IO) {
        artistRepository.insertArtist(artist)
    }

}