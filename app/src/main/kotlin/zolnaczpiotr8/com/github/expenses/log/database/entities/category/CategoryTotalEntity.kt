package zolnaczpiotr8.com.github.expenses.log.database.entities.category

import android.icu.math.BigDecimal
import androidx.room.ColumnInfo

data class CategoryTotalEntity(
    val uuid: String,
    val title: String,
    @ColumnInfo(
        name = "total_amount",
    )
    val totalAmount: BigDecimal,
)
