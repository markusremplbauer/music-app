package at.htl.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.navigation.NavHostController
import at.htl.shoppinglist.ui.screens.list.DrinkList
import at.htl.shoppinglist.ui.viewmodels.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val drinkViewModel by viewModels<DrinkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tabs(drinkViewModel)
        }
    }
}


@Composable
fun DrinksTab(drinkViewModel: DrinkViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        DrinkList(drinkList = drinkViewModel.drinkListResponse)
        drinkViewModel.getDrinkList()
    }
}


@Composable
fun Tabs(drinkViewModel: DrinkViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf("Drinks", "Test")
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        when (tabIndex) {
            0 -> DrinksTab(drinkViewModel = drinkViewModel)
        }
    }
}