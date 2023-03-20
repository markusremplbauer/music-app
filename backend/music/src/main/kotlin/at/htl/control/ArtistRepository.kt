package at.htl.control

import at.htl.entity.Artist
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ArtistRepository: PanacheRepository<Artist> {

    fun findByAlias(alias: String): Artist? {
        return find("alias", alias).firstResult()
    }
}