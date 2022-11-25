package at.htl.shoppinglist.ui.screens.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListScreen(
    navigateToEntryScreen: (Int) -> Unit
) {
//    Scaffold(
//        floatingActionButton = {
//
//        }
//    )
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToEntryScreen = {})
}