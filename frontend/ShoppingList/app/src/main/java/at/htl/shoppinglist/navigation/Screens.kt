package at.htl.shoppinglist.navigation

import androidx.navigation.NavHostController
import at.htl.shoppinglist.util.Action
import at.htl.shoppinglist.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}