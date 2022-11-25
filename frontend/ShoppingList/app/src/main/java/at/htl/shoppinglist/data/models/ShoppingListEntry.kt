package at.htl.shoppinglist.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import at.htl.shoppinglist.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
class ShoppingListEntry(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val amount: Int,
    val price: Double
) {
}