package zolnaczpiotr8.com.github.expenses.log.database.entities.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "category",
    indices =
        [
            Index(
                "title",
                unique = true,
            ),
        ],
)
data class CategoryEntity
constructor(
    @PrimaryKey val uuid: UUID,
    val title: String,
)
