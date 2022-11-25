package at.htl.shoppinglist.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import at.htl.shoppinglist.util.Action
import at.htl.shoppinglist.util.Constants

fun NavGraphBuilder.entryComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constants.ENTRY_SCREEN,
        arguments = listOf(navArgument(Constants.ENTRY_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}