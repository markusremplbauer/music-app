package at.htl.remplbauer.music.ui.artists

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.remplbauer.music.R
import at.htl.remplbauer.music.data.Artist
import coil.compose.AsyncImage

@Composable
fun ArtistsListScreen(viewModel: ArtistsViewModel) {
    val artists = viewModel.artists

    LazyColumn {
        items(artists) {
            ExpandableArtistCard(artist = it, viewModel = viewModel, color = Color.Black)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableArtistCard(
    artist: Artist,
    viewModel: ArtistsViewModel,
    color: Color,
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    var stroke by remember { mutableStateOf(1) } // Stroke State

    Card(
        modifier = Modifier
        .animateContentSize( // Animation
            animationSpec = tween(
                durationMillis = 400, // Animation Speed
                easing = LinearOutSlowInEasing // Animation Type
            )
        )
        .padding(8.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp), // Shape
        border = BorderStroke(stroke.dp, color), // Stroke Width and Color
        onClick = {
            expand = !expand
            stroke = if (expand) 2 else 1
        }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Control the header Alignment over here.
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    AsyncImage(
                        model = "https://api.dicebear.com/6.x/miniavs/png?seed=${artist.alias}",
                        error = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .size(88.dp)
                            .padding(4.dp),

                        )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = artist.alias,
                    color = color,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(.9f)
                        .padding(start = 8.dp)
                )
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    OutlinedButton(onClick = {
                        if (viewModel.isLiked(artist)) viewModel.removeArtist(artist)
                        else viewModel.addArtist(artist)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = if (viewModel.isLiked(artist)) {
                                Color.Yellow
                            } else {
                                Color.Gray
                            }
                        )
                        Text("Like")
                    }
                }
                IconButton(modifier = Modifier
                    .rotate(rotationState)
                    .weight(.1f), onClick = {
                    expand = !expand
                    stroke = if (expand) 2 else 1
                }) {
                    Icon(
                        imageVector = if (expand) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        tint = color, // Icon Color
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expand) {
                Row {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "${artist.firstName} ${artist.lastName}",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = artist.info,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}