package zolnaczpiotr8.com.github.expenses.log.core.model

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import java.math.BigInteger
import java.util.Locale
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Category
@OptIn(ExperimentalUuidApi::class)
constructor(
    val uuid: Uuid,
    val title: String,
    val totalAmount: CurrencyAmount,
) {

    companion object {
        @OptIn(ExperimentalUuidApi::class)
        val FOOD = Category(
            uuid = Uuid.random(),
            title = "Food",
            totalAmount = CurrencyAmount(
                BigInteger.TEN,
                Currency.getInstance(Locale.US),
            ),
        )
    }
}
