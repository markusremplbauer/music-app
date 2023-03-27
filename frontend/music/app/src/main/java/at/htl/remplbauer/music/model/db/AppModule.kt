package at.htl.remplbauer.music.model.db

import android.content.Context
import androidx.room.Room
import at.htl.remplbauer.music.model.ArtistRepository
import at.htl.remplbauer.music.model.db.dao.ArtistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "music_db"
        ).build()
    }

    @Provides
    fun provideArtistDao(database: AppDatabase) : ArtistDao {
        return database.artistDao()
    }


    @Provides
    fun provideArtistRepo(dao: ArtistDao) : ArtistRepository {
        return ArtistRepository(dao)
    }

}