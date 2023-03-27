package at.htl.remplbauer.music.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val alias: String,
    val info: String
)