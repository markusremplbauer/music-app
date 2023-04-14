package at.htl.remplbauer.music.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import at.htl.remplbauer.music.data.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Query("select alias, firstName, lastName from artists")
    fun getAll(): LiveData<List<Artist>>

    @Query("select alias, firstName, lastName from artists where id = :authorId")
    fun getById(authorId: Int): Flow<Artist>

    @Query("select alias, firstName, lastName from artists where alias LIKE :alias LIMIT 1")
    fun findByAlias(alias: String): Artist?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: Artist)

    @Delete
    suspend fun delete(author: Artist)
}