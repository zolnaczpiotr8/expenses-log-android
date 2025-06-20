package zolnaczpiotr8.com.github.expenses.log.model

import android.icu.util.CurrencyAmount
import kotlinx.collections.immutable.ImmutableList

data class Categories(
    val totalAmount: CurrencyAmount,
    val categories: ImmutableList<Category>,
)
