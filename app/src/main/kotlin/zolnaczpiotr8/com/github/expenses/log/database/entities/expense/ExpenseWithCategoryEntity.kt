package zolnaczpiotr8.com.github.expenses.log.database.entities.expense

import androidx.room.ColumnInfo
import java.time.Instant
import java.util.UUID

data class ExpenseWithCategoryEntity(
    val title: String?,
    val uuid: UUID,
    val amount: Double,
    @ColumnInfo(
        name = "category_title",
    )
    val categoryTitle: String,
    val created: Instant,
)
