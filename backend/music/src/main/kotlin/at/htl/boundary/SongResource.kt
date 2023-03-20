package at.htl.boundary

import at.htl.boundary.dto.ArtistDto
import at.htl.boundary.dto.SongDto
import at.htl.control.ArtistRepository
import at.htl.control.GenreRepository
import at.htl.control.SongRepository
import at.htl.entity.Artist
import at.htl.entity.Song
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("/song")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class SongResource {

    @Inject
    lateinit var songRepository: SongRepository

    @Inject
    lateinit var artistRepository: ArtistRepository

    @Inject
    lateinit var genreRepository: GenreRepository

    @GET
    fun getAll(): Response {
        val songs = songRepository.listAll()

        return Response.ok(songs).build()
    }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Response {
        val song = songRepository.findById(id)

        return when (song) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                Response.ok(song).build()
            }
        }
    }

    @POST
    @Transactional
    fun add(songDto: SongDto): Response {
        val song = Song(
            songDto.title,
            artistRepository.findById(songDto.artistId),
            songDto.genreIds.map { genreRepository.findById(it) }
        )

        songRepository.persist(song)

        return Response.created(
            UriBuilder.fromMethod(ArtistResource::class.java, "getById")
                .build(song.id)
        ).build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deleteById(@PathParam("id") id: Long): Response {
        val song = songRepository.findById(id)

        return when (song) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                songRepository.delete(song)
                Response.noContent().build()
            }
        }
    }
}