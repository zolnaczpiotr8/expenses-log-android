package zolnaczpiotr8.com.github.expenses.log.core.database.model.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
    @OptIn(ExperimentalUuidApi::class)
    val uuid: Uuid,
    val title: String,
)
