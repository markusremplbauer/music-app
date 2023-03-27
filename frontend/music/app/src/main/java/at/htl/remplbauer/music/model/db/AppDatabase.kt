package at.htl.remplbauer.music.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import at.htl.remplbauer.music.data.Artist
import at.htl.remplbauer.music.model.db.dao.ArtistDao

@Database(entities = [Artist::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistDao
}