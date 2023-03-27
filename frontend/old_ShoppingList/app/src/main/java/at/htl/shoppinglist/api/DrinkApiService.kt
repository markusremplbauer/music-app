package at.htl.shoppinglist.api

import at.htl.shoppinglist.data.dto.DrinksDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DrinkApiService {

    @GET("search.php?s=")
    suspend fun getDrinks(): DrinksDto

    companion object {
        private var apiService: DrinkApiService? = null

        fun getInstance(): DrinkApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DrinkApiService::class.java)
            }
            return apiService!!
        }
    }
}