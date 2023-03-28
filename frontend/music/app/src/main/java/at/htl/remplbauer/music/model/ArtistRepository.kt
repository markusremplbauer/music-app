package at.htl.remplbauer.music.model

import androidx.lifecycle.LiveData
import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.model.api.ApiService
import at.htl.remplbauer.music.model.db.dao.ArtistDao
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val artistDao: ArtistDao
) {
    private val apiService: ApiService = ApiService.getInstance()

    suspend fun getArtists(): List<Artist> {
        return apiService.getArtists()
    }


    // ----------------------------------------------------
    //      DATABASE ACCESS
    // ----------------------------------------------------
    suspend fun insertArtist(artist: Artist) {
        artistDao.insert(artist)
    }

    suspend fun deleteArtist(artist: Artist) {
        artistDao.delete(artist)
    }


    fun getFavouriteArtists(): LiveData<List<Artist>> = artistDao.getAll();
}