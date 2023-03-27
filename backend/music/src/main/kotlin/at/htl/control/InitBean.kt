package at.htl.control

import at.htl.entity.Artist
import at.htl.entity.Genre
import at.htl.entity.Song
import io.quarkus.runtime.StartupEvent
import org.jboss.logging.Logger
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

    @Inject
    lateinit var LOG: Logger

    fun init(@Observes event: StartupEvent) {
        readArtistsFromCsv()
        readGenresFromCsv()
        readSongsFromCsv()
    }

    @Transactional
    fun readArtistsFromCsv() {
        try {
            Files.lines(Paths.get("artist.csv")).use { lines ->
                lines.skip(1).forEach { l ->
                    val line = l.split(";")
                    val artist = Artist(
                        firstName = line[0], lastName = line[1], alias = line[2], info = line[3]
                    )
                    artistRepository.persist(artist)
                }
            }
        } catch (e: IOException) {
            LOG.error("Failed to read artists from CSV file", e)
        }
    }

    @Transactional
    fun readGenresFromCsv() {
        try {
            Files.lines(Paths.get("genres.csv")).use { lines ->
                lines.skip(1).forEach { l ->
                    val line = l.split(";")
                    val genre = Genre(
                        name = line[0], description = line[1]
                    )
                    genreRepository.persist(genre)
                }
            }
        } catch (e: IOException) {
            LOG.error("Failed to read genres from CSV file", e)
        }
    }

    @Transactional
    fun readSongsFromCsv() {
        try {
            Files.lines(Paths.get("songs.csv")).use { lines ->
                lines.skip(1).forEach { l ->
                    val line = l.split(";")
                    val song = Song(
                        title = line[0],
                        artist = artistRepository.findByAlias(line[1])!!,
                        genres = line[2].split(", ").map { genreRepository.findByName(it)!! },
                    )
                    songRepository.persist(song)
                }
            }

        } catch (e: IOException) {
            LOG.error("Failed to read songs from CSV file", e)
        }
    }
}