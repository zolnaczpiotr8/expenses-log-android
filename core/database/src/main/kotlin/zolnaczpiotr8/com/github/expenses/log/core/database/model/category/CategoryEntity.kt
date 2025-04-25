package zolnaczpiotr8.com.github.expenses.log.core.database.model.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    indices = [
        Index(
            "title",
            unique = true,
        ),
    ],
)
data class CategoryEntity(
    @PrimaryKey
    val uuid: String,
    val title: String,
)
