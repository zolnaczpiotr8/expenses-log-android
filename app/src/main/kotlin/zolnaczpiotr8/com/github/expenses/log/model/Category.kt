package zolnaczpiotr8.com.github.expenses.log.model

import android.icu.util.CurrencyAmount

data class Category(
    val uuid: String,
    val title: String,
    val totalAmount: CurrencyAmount,
)
