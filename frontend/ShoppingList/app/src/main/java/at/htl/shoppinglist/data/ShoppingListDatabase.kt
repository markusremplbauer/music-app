package at.htl.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import at.htl.shoppinglist.data.models.ShoppingListEntry

@Database(entities = [ShoppingListEntry::class], version = 1, exportSchema = false)
abstract class ShoppingListDatabase: RoomDatabase() {

    abstract fun shoppingListEntryDao(): ShoppingListEntryDao

}