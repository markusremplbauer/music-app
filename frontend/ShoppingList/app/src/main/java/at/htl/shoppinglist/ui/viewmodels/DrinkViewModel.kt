package at.htl.shoppinglist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.shoppinglist.api.DrinkApiService
import at.htl.shoppinglist.data.models.Drink
import kotlinx.coroutines.launch

class DrinkViewModel: ViewModel() {
    var drinkListResponse: List<Drink> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getDrinkList() {
        viewModelScope.launch {
            val apiService = DrinkApiService.getInstance()

            try {
                val drinksDto = apiService.getDrinks()
                drinkListResponse = drinksDto.drinks
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}