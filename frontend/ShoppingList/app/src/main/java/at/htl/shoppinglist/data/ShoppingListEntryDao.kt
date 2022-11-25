package at.htl.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import at.htl.shoppinglist.data.models.ShoppingListEntry
import at.htl.shoppinglist.util.Constants.DATABASE_SHOPPING_LIST_ENTRY_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListEntryDao {

    @Query("SELECT * FROM $DATABASE_SHOPPING_LIST_ENTRY_TABLE ORDER BY id ASC")
    fun getAllEntries(): Flow<List<ShoppingListEntry>>

    @Query("SELECT * FROM $DATABASE_SHOPPING_LIST_ENTRY_TABLE WHERE id=:id")
    fun getSelectedEntry(id: Int): Flow<ShoppingListEntry>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(shoppingListEntry: ShoppingListEntry)

    @Update
    suspend fun updateEntry(shoppingListEntry: ShoppingListEntry)

    @Delete
    suspend fun deleteEntry(shoppingListEntry: ShoppingListEntry)

    @Query("DELETE FROM $DATABASE_SHOPPING_LIST_ENTRY_TABLE")
    suspend fun deleteAllEntries()

    @Query("SELECT * FROM $DATABASE_SHOPPING_LIST_ENTRY_TABLE WHERE name LIKE :searchQuery OR amount LIKE :searchQuery OR price LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ShoppingListEntry>>
}