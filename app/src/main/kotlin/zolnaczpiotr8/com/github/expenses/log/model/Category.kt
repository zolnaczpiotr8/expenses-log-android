package zolnaczpiotr8.com.github.expenses.log.model

import android.icu.util.CurrencyAmount
import java.util.UUID

data class Category(
    val uuid: UUID,
    val title: String,
    val totalAmount: CurrencyAmount,
)
