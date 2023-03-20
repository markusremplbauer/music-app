package at.htl.control

import at.htl.entity.Artist
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

    fun init(@Observes event: StartupEvent) {
        readArtistsFromCsv()
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
}