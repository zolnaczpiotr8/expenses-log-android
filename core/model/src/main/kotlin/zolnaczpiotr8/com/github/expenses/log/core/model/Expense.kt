package zolnaczpiotr8.com.github.expenses.log.core.model

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import zolnaczpiotr8.com.github.expenses.log.core.model.Category.Companion.FOOD
import java.math.BigInteger
import java.util.Locale
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Expense
@OptIn(ExperimentalUuidApi::class)
constructor(
    val title: String?,
    val categoryTitle: String,
    val amount: CurrencyAmount,
    val uuid: Uuid,
) {
    companion object {
        @OptIn(ExperimentalUuidApi::class)
        val BOTTLE_OF_WATER = Expense(
            title = "A bottle of water",
            categoryTitle = FOOD.title,
            amount = CurrencyAmount(
                BigInteger.TEN,
                Currency.getInstance(Locale.US),
            ),
            uuid = Uuid.random(),
        )

        @OptIn(ExperimentalUuidApi::class)
        val NO_TITLE = Expense(
            title = null,
            categoryTitle = FOOD.title,
            amount = CurrencyAmount(
                BigInteger.ONE,
                Currency.getInstance(Locale.US),
            ),
            uuid = Uuid.random(),
        )
    }
}
