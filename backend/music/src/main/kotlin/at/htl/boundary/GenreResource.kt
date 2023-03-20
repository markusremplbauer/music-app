package at.htl.boundary

import at.htl.boundary.dto.GenreDto
import at.htl.control.GenreRepository
import at.htl.entity.Genre
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("/genre")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class GenreResource {

    @Inject
    lateinit var genreRepository: GenreRepository

    @GET
    fun getAll(): Response {
        val genres = genreRepository.listAll()
        return Response.ok(genres).build()
    }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: Long): Response {
        val genre = genreRepository.findById(id)

        return when (genre) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                Response.ok(genre).build()
            }
        }
    }

    @POST
    @Transactional
    fun add(genreDto: GenreDto): Response {
        val genre = Genre(genreDto.name, genreDto.descrption)

        genreRepository.persist(genre)

        return Response.created(
            UriBuilder.fromMethod(GenreResource::class.java, "getById")
                .build(genre.id)
        ).build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deleteById(@PathParam("id") id: Long): Response {
        val genre = genreRepository.findById(id)

        return when (genre) {
            null -> Response.status(Response.Status.NOT_FOUND).build()
            else -> {
                genreRepository.delete(genre)
                Response.noContent().build()
            }
        }
    }
}