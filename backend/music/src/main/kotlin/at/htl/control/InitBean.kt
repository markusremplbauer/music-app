package at.htl.control

import at.htl.entity.Artist
import at.htl.entity.Genre
import at.htl.entity.Song
import io.quarkus.runtime.StartupEvent
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.transaction.Transactional

class InitBean {

    @Inject
    lateinit var artistRepository: ArtistRepository

    @Inject
    lateinit var songRepository: SongRepository

    @Inject
    lateinit var genreRepository: GenreRepository

    fun init(@Observes event: StartupEvent) {
        readArtistsFromCsv()
        readGenresFromCsv()
        readSongsFromCsv()
    }

    @Transactional
    fun readArtistsFromCsv() {
        try {
            val lines = Files.lines(Paths.get("artist.csv"))
            lines.skip(1)
                .forEach { l ->
                    val line = l.split(";")
                    val artist = Artist(line[0], line[1], line[2], line[3])
                    artistRepository.persist(artist)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Transactional
    fun readGenresFromCsv() {
        try {
            val lines = Files.lines(Paths.get("genres.csv"))
            lines.skip(1)
                .forEach { l ->
                    val line = l.split(";")
                    val genre = Genre(
                        line[0],
                        line[1]
                    )
                    genreRepository.persist(genre)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Transactional
    fun readSongsFromCsv() {
        try {
            val lines = Files.lines(Paths.get("songs.csv"))
            lines.skip(1)
                .forEach { l ->
                    val line = l.split(";")
                    val song = Song(
                        line[0],
                        artistRepository.findByAlias(line[1]),
                        line[2].split(", ").map { genreRepository.findByName(it) },
                    )
                    songRepository.persist(song)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


//    @Transactional
//    fun readGenresFromCsv(){
//        try {
//            val lines = Files.lines(Paths.get("genres.csv"))
//            val genres = mutableSetOf<String>()
//
//            lines.skip(1)
//                .forEach { l ->
//                    for (genreName in l.split(",")) {
//                        genres.add(genreName)
//                    }
//                }
//
//            for (genreName in genres) {
//                val persistedGenre = genreRepository.findByName(genreName)
//                if (persistedGenre == null) {
//                    val genre = Genre(genreName, "Description for $genreName")
//                    genreRepository.persist(genre)
//                }
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }

}