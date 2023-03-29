package at.htl.remplbauer.music.model

import at.htl.remplbauer.music.data.Song
import at.htl.remplbauer.music.model.api.ApiService
import javax.inject.Inject

class SongRepository @Inject constructor() {
    private val apiService: ApiService = ApiService.getInstance()

    suspend fun getSongs(): List<Song> {
        return apiService.getSongs()
    }
}