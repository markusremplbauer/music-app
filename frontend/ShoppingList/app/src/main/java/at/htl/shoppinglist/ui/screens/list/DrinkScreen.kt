package at.htl.shoppinglist.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.shoppinglist.data.models.Drink
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import at.htl.shoppinglist.R


@Composable
fun DrinkItem(drink: Drink) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(data = drink.strDrinkThumb,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.placeholder)
                            transformations(CircleCropTransformation())

                        }),
                    contentDescription = drink.strDrink,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {

                    Text(
                        text = drink.strDrink,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = drink.strCategory,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = drink.strAlcoholic,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color(0xFFE57373),
                                RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = drink.strInstructions,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DrinkList(drinkList: List<Drink>) {
    LazyColumn {
        itemsIndexed(items = drinkList) { index, item ->
            DrinkItem(drink = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {

    val drink = Drink(
        idDrink = "11000",
        strDrink = "Mojito",
        strCategory = "Cocktail",
        strAlcoholic = "Alcoholic",
        strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
        strInstructions = "Muddle mint leaves with sugar and lime juice. Add a splash of soda water and fill the glass with cracked ice. Pour the rum and top with soda water. Garnish and serve with straw."
    )

    DrinkItem(drink = drink)
}
