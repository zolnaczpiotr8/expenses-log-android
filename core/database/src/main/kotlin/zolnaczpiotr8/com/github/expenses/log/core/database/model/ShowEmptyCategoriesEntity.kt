package zolnaczpiotr8.com.github.expenses.log.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "show_empty_categories",
)
data class ShowEmptyCategoriesEntity(
    @PrimaryKey
    val id: Int = 1,
    val value: Boolean,
)
