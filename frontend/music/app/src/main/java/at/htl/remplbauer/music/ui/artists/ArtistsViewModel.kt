package at.htl.remplbauer.music.ui.artists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
) : ViewModel() {
    var artists: List<Artist> by mutableStateOf((emptyList()))
    var favouriteArtists: List<Artist> by mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            artists = getArtists()

            artistRepository.getFavouriteArtists().observeForever { sections ->
                if (sections == null || sections.isEmpty()) {
                    // No data in your database, call your api for data
                } else {
                    favouriteArtists = favouriteArtists + sections;
                }
            }
        }
    }

    private suspend fun getArtists(): List<Artist> {
        return artistRepository.getArtists()
    }


    fun addArtist(artist: Artist) = viewModelScope.launch(Dispatchers.IO) {
        artistRepository.insertArtist(artist)
        favouriteArtists = listOf()
        artistRepository.getFavouriteArtists()
    }
}