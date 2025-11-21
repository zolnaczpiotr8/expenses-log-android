package zolnaczpiotr8.com.github.expenses.log.database.entities.category

import androidx.room.ColumnInfo
import java.util.UUID

data class CategoryTotalEntity(
    val uuid: UUID,
    val title: String,
    @ColumnInfo(
        name = "total_amount",
    )
    val totalAmount: Double,
)
