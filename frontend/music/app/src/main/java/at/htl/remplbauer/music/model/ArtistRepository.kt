package at.htl.remplbauer.music.model

import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.model.api.ApiService
import at.htl.remplbauer.music.model.db.dao.ArtistDao
import kotlinx.coroutines.flow.Flow
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
        if(artistDao.findByAlias(artist.alias) == null) {
            artistDao.insert(artist)
        }
    }

    fun getAllArtistsFromDB(): Flow<List<Artist>>{
        return artistDao.getAll()
    }
}