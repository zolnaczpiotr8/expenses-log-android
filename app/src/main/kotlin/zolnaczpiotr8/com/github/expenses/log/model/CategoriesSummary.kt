package zolnaczpiotr8.com.github.expenses.log.model

import android.icu.util.CurrencyAmount

data class CategoriesSummary(
    val totalAmount: CurrencyAmount,
    val categories: List<Category>,
)
