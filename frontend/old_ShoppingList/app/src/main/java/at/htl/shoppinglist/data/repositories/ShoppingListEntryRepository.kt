package at.htl.shoppinglist.data.repositories

import at.htl.shoppinglist.data.ShoppingListEntryDao
import at.htl.shoppinglist.data.models.ShoppingListEntry
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ShoppingListEntryRepository @Inject constructor(private val shoppingListEntryDao: ShoppingListEntryDao) {

    val getAllEntries = shoppingListEntryDao.getAllEntries()

    fun getSelectedEntry(entryId: Int) {
        shoppingListEntryDao.getSelectedEntry(entryId = entryId)
    }

    suspend fun addEntry(entry: ShoppingListEntry) {
        shoppingListEntryDao.addEntry(shoppingListEntry = entry)
    }

    suspend fun updateEntry(entry: ShoppingListEntry) {
        shoppingListEntryDao.updateEntry(shoppingListEntry = entry)
    }

    suspend fun deleteEntry(entry: ShoppingListEntry) {
        shoppingListEntryDao.deleteEntry(shoppingListEntry = entry)
    }

    suspend fun deleteAllEntries() {
        shoppingListEntryDao.deleteAllEntries()
    }

    fun searchDatabase(searchQuery: String) {
        shoppingListEntryDao.searchDatabase(searchQuery = searchQuery)
    }
}