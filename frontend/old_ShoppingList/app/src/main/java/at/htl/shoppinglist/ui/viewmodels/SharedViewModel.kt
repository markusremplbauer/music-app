package at.htl.shoppinglist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.shoppinglist.data.models.ShoppingListEntry
import at.htl.shoppinglist.data.repositories.ShoppingListEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor (
    private val repository: ShoppingListEntryRepository
): ViewModel() {

    private val _allEntries =
        MutableStateFlow<List<ShoppingListEntry>>(emptyList())
    val allTasks: StateFlow<List<ShoppingListEntry>> = _allEntries

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllEntries.collect {
                _allEntries.value = it
            }
        }
    }
}