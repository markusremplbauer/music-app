package at.htl.remplbauer.music.ui.artists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import at.htl.remplbauer.music.R
import coil.compose.AsyncImage

@Composable
fun LikedArtistsScreen(viewModel: ArtistsViewModel) {
    val artists = viewModel.favouriteArtists

    LazyColumn {
        items(artists) { artist ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row {
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
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = artist.alias,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
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