package at.htl.control

import at.htl.entity.Genre
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GenreRepository: PanacheRepository<Genre> {
    fun findByName(genreName: String): Genre? {
        return find("name", genreName).firstResult()
    }
}