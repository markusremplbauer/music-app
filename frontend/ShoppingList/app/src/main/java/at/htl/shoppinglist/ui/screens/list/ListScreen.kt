package at.htl.shoppinglist.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import at.htl.shoppinglist.ui.theme.fabBackgroundColor
import at.htl.shoppinglist.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToEntryScreen: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            ListAppBar()
        },
        content = {},
        floatingActionButton = {
            ListFab(onFabClicked = navigateToEntryScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = Color.White
        )
    }
}



@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToEntryScreen = {})
}