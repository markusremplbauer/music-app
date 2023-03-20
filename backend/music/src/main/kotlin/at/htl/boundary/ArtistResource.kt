package at.htl.boundary

import at.htl.boundary.dto.ArtistDto
import at.htl.control.ArtistRepository
import at.htl.entity.Artist
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("/artist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ArtistResource {

    @Inject
    lateinit var artistRepository: ArtistRepository

    @GET
    fun getAll(): Response {
        val list = artistRepository.listAll()
        return Response.ok(list).build()
    }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Response {
        val artist = artistRepository.findById(id)

        return when (artist) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                Response.ok(artist).build()
            }
        }
    }

    @POST
    @Transactional
    fun add(artistDto: ArtistDto): Response {
        val artist = Artist(artistDto.firstName, artistDto.lastName, artistDto.alias, artistDto.info)
        artistRepository.persist(artist)

        return Response.created(
            UriBuilder.fromMethod(ArtistResource::class.java, "getById")
                .build(artist.id)
        ).build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deleteById(@PathParam("id") id: Long): Response {
        val artist = artistRepository.findById(id)

        return when (artist) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                artistRepository.delete(artist)
                Response.noContent().build()
            }
        }
    }
}