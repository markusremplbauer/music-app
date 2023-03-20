package at.htl.control

import at.htl.entity.Song
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SongRepository : PanacheRepository<Song> {
}