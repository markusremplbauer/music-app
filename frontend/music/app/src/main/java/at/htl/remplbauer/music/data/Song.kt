package at.htl.remplbauer.music.data

data class Song(
    val id: Long,
    val title: String,
    val artist: Artist,
    val genres: List<Genre>
)