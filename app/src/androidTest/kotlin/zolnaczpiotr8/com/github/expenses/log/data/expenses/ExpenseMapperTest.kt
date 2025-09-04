package zolnaczpiotr8.com.github.expenses.log.data.expenses

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseWithCategoryEntity
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpenseMapperTest {

  private val currency = Currency.getInstance("USD")
  private val subject = ExpenseMapper()

  @Test
  fun empty() {
    val actual = subject.map(expenses = emptyList(), currency = currency)

    assert(actual.isEmpty())
  }

  @Test
  fun one() {
    val expense =
        ExpenseWithCategoryEntity(
            title = "Expense",
            uuid = UUID.randomUUID(),
            amount = 1.0,
            categoryTitle = "Category",
            created = Instant.now(),
        )

    val actual = subject.map(expenses = listOf(expense), currency = currency)

    val expected =
        listOf(
            Expense(
                title = expense.title,
                categoryTitle = expense.categoryTitle,
                amount = CurrencyAmount(expense.amount, currency),
                uuid = expense.uuid,
                created = expense.created.atZone(ZoneId.systemDefault()).toLocalDate(),
            )
        )

    assert(actual == expected)
  }

  @Test
  fun multiple() {
    val expense =
        ExpenseWithCategoryEntity(
            title = "Expense",
            uuid = UUID.randomUUID(),
            amount = 1.0,
            categoryTitle = "Category",
            created = Instant.now(),
        )

    val expense1 =
        ExpenseWithCategoryEntity(
            title = "Expense1",
            uuid = UUID.randomUUID(),
            amount = 11111.0,
            categoryTitle = "Category",
            created = Instant.now(),
        )

    val expense2 =
        ExpenseWithCategoryEntity(
            title = "Expense2",
            uuid = UUID.randomUUID(),
            amount = 111.0,
            categoryTitle = "Category",
            created = Instant.now(),
        )

    val expense3 =
        ExpenseWithCategoryEntity(
            title = "Expense3",
            uuid = UUID.randomUUID(),
            amount = 11.0,
            categoryTitle = "Category",
            created = Instant.now(),
        )

    val actual =
        subject.map(expenses = listOf(expense, expense1, expense2, expense3), currency = currency)

    val expected =
        listOf(
            Expense(
                title = expense.title,
                categoryTitle = expense.categoryTitle,
                amount = CurrencyAmount(expense.amount, currency),
                uuid = expense.uuid,
                created = expense.created.atZone(ZoneId.systemDefault()).toLocalDate(),
            ),
            Expense(
                title = expense1.title,
                categoryTitle = expense1.categoryTitle,
                amount = CurrencyAmount(expense1.amount, currency),
                uuid = expense1.uuid,
                created = expense.created.atZone(ZoneId.systemDefault()).toLocalDate(),
            ),
            Expense(
                title = expense2.title,
                categoryTitle = expense2.categoryTitle,
                amount = CurrencyAmount(expense2.amount, currency),
                uuid = expense2.uuid,
                created = expense.created.atZone(ZoneId.systemDefault()).toLocalDate(),
            ),
            Expense(
                title = expense3.title,
                categoryTitle = expense3.categoryTitle,
                amount = CurrencyAmount(expense3.amount, currency),
                uuid = expense3.uuid,
                created = expense.created.atZone(ZoneId.systemDefault()).toLocalDate(),
            ),
        )

    assert(actual == expected)
  }
}
