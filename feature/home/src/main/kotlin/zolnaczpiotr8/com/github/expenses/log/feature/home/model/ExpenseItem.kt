package zolnaczpiotr8.com.github.expenses.log.feature.home.model

import android.icu.util.CurrencyAmount
import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

@Stable
interface ExpenseItem {

    val contentType: Int

    data class Header(
        val date: LocalDate,
    ) : ExpenseItem {
        override val contentType = 1
    }

    data class Expense(
        val title: String?,
        val categoryTitle: String,
        val amount: CurrencyAmount,
        val uuid: String,
    ) : ExpenseItem {
        override val contentType = 2
    }
}
