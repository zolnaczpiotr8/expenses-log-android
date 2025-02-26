package zolnaczpiotr8.com.github.expenses.log.core.model

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.math.BigDecimal

data class Categories(
    val totalAmount: CurrencyAmount,
    val categories: ImmutableList<Category>,
) {

    fun isNotEmpty(): Boolean = categories.isNotEmpty()
    fun isEmpty(): Boolean = categories.isEmpty()

    companion object {
        val EMPTY = Categories(
            totalAmount = CurrencyAmount(
                BigDecimal.ZERO,
                Currency.getInstance(ULocale.US),
            ),
            categories = persistentListOf(),
        )
        val ONE = Categories(
            totalAmount = Category.FOOD.totalAmount,
            categories = persistentListOf(Category.FOOD),
        )
    }
}
