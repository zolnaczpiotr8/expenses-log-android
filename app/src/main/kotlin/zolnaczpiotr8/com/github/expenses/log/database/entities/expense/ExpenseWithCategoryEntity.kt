package zolnaczpiotr8.com.github.expenses.log.database.entities.expense

import android.icu.math.BigDecimal
import androidx.room.ColumnInfo
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class ExpenseWithCategoryEntity
@OptIn(ExperimentalTime::class)
constructor(
    val title: String?,
    val uuid: String,
    val amount: BigDecimal,
    @ColumnInfo(
        name = "category_title",
    )
    val categoryTitle: String,
    val created: Instant,
)
