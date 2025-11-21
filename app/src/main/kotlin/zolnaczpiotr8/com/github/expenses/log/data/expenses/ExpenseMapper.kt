package zolnaczpiotr8.com.github.expenses.log.data.expenses

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import java.time.ZoneId
import javax.inject.Inject
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseWithCategoryEntity
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpenseMapper @Inject constructor() {

  fun map(expenses: List<ExpenseWithCategoryEntity>, currency: Currency): List<Expense> =
      expenses.map {
        Expense(
            title = it.title,
            categoryTitle = it.categoryTitle,
            amount =
                CurrencyAmount(
                    it.amount,
                    currency,
                ),
            uuid = it.uuid,
            created = it.created.atZone(ZoneId.systemDefault()).toLocalDate(),
        )
      }
}
