package at.htl.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import at.htl.shoppinglist.ui.screens.list.DrinkList
import at.htl.shoppinglist.ui.viewmodels.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val drinkViewModel by viewModels<DrinkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            TODO update when continuing with the tutorial
//            ShoppingListTheme {
//                navController = rememberNavController()
//                SetupNavigation(navController = navController)
//            }
            Surface(color = MaterialTheme.colors.background) {
                DrinkList(drinkList = drinkViewModel.drinkListResponse)
                drinkViewModel.getDrinkList()
            }
        }
    }
}