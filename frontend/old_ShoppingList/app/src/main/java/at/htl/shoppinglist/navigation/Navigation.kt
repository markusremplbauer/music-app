package at.htl.shoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import at.htl.shoppinglist.navigation.destinations.entryComposable
import at.htl.shoppinglist.navigation.destinations.listComposable
import at.htl.shoppinglist.util.Constants

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task
        )
        entryComposable(
            navigateToListScreen = screen.list
        )
    }

}