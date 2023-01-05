package at.htl.shoppinglist.data.models

import androidx.compose.ui.graphics.Color
import at.htl.shoppinglist.ui.theme.HighPriorityColor
import at.htl.shoppinglist.ui.theme.LowPriorityColor
import at.htl.shoppinglist.ui.theme.MediumPriorityColor
import at.htl.shoppinglist.ui.theme.NonePriorityColor


enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}