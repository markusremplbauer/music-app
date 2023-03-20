package at.htl.control

import at.htl.entity.Artist
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

    fun init(@Observes event: StartupEvent) {
        readArtistsFromCsv()
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
    fun readSongsFromCsv(){
        try {
            val lines = Files.lines(Paths.get("songs.csv"))
            lines.skip(1)
                .forEach { l ->
                    val line = l.split(";")
                    val song = Song(
                        line[0],
                        artistRepository.findByAlias(line[1])
                    )
                    songRepository.persist(song)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}