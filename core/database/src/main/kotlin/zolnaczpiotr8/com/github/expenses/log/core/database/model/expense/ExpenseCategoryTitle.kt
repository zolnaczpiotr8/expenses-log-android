package zolnaczpiotr8.com.github.expenses.log.core.database.model.expense

import android.icu.math.BigDecimal

data class ExpenseCategoryTitle(
    val uuid: String,
    val title: String?,
    val amount: BigDecimal,
    val categoryTitle: String,
)
