package at.htl.remplbauer.music.model.api

import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.data.Song
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {

    companion object{
        private var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }


    @GET("artist")
    suspend fun getArtists(): List<Artist>

    @GET("song")
    suspend fun getSongs(): List<Song>
}