package zolnaczpiotr8.com.github.expenses.log.core.model

import android.icu.util.CurrencyAmount
import kotlinx.datetime.LocalDate

data class Expense(
    val title: String?,
    val categoryTitle: String,
    val amount: CurrencyAmount,
    val uuid: String,
    val created: LocalDate,
)
