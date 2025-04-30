package zolnaczpiotr8.com.github.expenses.log.core.database.model.expense

import android.icu.math.BigDecimal
import androidx.room.ColumnInfo
import kotlinx.datetime.Instant

data class ExpenseWithCategoryEntity(
    val title: String?,
    val uuid: String,
    val amount: BigDecimal,
    @ColumnInfo(
        name = "category_title",
    )
    val categoryTitle: String,
    val created: Instant,
)
