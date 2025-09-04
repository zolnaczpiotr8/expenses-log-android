package zolnaczpiotr8.com.github.expenses.log.model

import android.icu.util.CurrencyAmount
import java.time.LocalDate
import java.util.UUID

data class Expense(
    val title: String?,
    val categoryTitle: String,
    val amount: CurrencyAmount,
    val uuid: UUID,
    val created: LocalDate,
)
