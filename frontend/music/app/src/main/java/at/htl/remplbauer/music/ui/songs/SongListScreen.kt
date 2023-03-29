package at.htl.remplbauer.music.ui.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import at.htl.remplbauer.music.R

@Composable
fun SongListScreen(viewModel: SongsViewModel) {
    val songs = viewModel.songs

    LazyColumn {
        items(songs) { song ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row{
                    // make a column with the image and center it
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        AsyncImage(
                            model = "https://api.dicebear.com/6.x/shapes/png?seed=${song.title}",
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
                            text = song.title,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            song.genres.forEach {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier
                                        .background(
                                            Color(Cyan.value),
                                            RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                        Text(
                            text = song.artist.alias,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}