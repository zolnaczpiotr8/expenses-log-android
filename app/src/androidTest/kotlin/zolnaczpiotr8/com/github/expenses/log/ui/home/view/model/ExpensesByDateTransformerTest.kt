package zolnaczpiotr8.com.github.expenses.log.ui.home.view.model

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpensesByDateTransformerTest {

  private val currency = Currency.getInstance(ULocale.getDefault())

  @Test
  fun givenEmptyList_whenTransform_thenReturnsEmptyList() = runTest {
    val transformer = ExpensesByDateTransformer(dispatcher = StandardTestDispatcher(testScheduler))

    val actual = transformer.transform(emptyList())
    advanceUntilIdle()
    val expected: Map<LocalDate, List<Expense>> = emptyMap()
    assert(actual == expected)
  }

  @Test
  fun givenSingleExpense_whenTransform_thenReturnsSingleDateGroup() = runTest {
    val transformer = ExpensesByDateTransformer(dispatcher = StandardTestDispatcher(testScheduler))

    val date = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate()
    val expense =
        Expense(
            title = "",
            categoryTitle = "",
            amount = CurrencyAmount(10, currency),
            uuid = UUID.randomUUID(),
            created = date,
        )
    val actual = transformer.transform(listOf(expense))
    advanceUntilIdle()
    val expected: Map<LocalDate, List<Expense>> = mapOf(date to listOf(expense))

    assert(actual == expected)
  }

  @Test
  fun givenMultipleExpensesWithSameDate_whenTransform_thenGroupsUnderSameDate() = runTest {
    val transformer = ExpensesByDateTransformer(dispatcher = StandardTestDispatcher(testScheduler))

    val date = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate()
    val coffeeExpense =
        Expense(
            title = "Coffee",
            categoryTitle = "Food & Drinks",
            amount = CurrencyAmount(5, currency),
            uuid = UUID.randomUUID(),
            created = date,
        )
    val lunchExpense =
        Expense(
            title = "Lunch",
            categoryTitle = "Food & Drinks",
            amount = CurrencyAmount(15, currency),
            uuid = UUID.randomUUID(),
            created = date,
        )
    val parkingExpense =
        Expense(
            title = "Parking",
            categoryTitle = "Transport",
            amount = CurrencyAmount(8, currency),
            uuid = UUID.randomUUID(),
            created = date,
        )

    val expensesWithSameDate = listOf(coffeeExpense, lunchExpense, parkingExpense)

    val actual = transformer.transform(expensesWithSameDate)
    advanceUntilIdle()
    val expected: Map<LocalDate, List<Expense>> =
        mapOf(date to listOf(coffeeExpense, lunchExpense, parkingExpense))

    assert(actual == expected)
  }

  @Test
  fun givenExpensesWithDifferentDates_whenTransform_thenGroupsAndSortsDescendingByDate() = runTest {
    val transformer = ExpensesByDateTransformer(dispatcher = StandardTestDispatcher(testScheduler))

    val firstDate = LocalDate.ofEpochDay(1)
    val secondDate = LocalDate.ofEpochDay(2)

    val coffeeExpense =
        Expense(
            title = "Coffee",
            categoryTitle = "Food & Drinks",
            amount = CurrencyAmount(5, currency),
            uuid = UUID.randomUUID(),
            created = secondDate,
        )
    val lunchExpense =
        Expense(
            title = "Lunch",
            categoryTitle = "Food & Drinks",
            amount = CurrencyAmount(15, currency),
            uuid = UUID.randomUUID(),
            created = firstDate,
        )
    val parkingExpense =
        Expense(
            title = "Parking",
            categoryTitle = "Transport",
            amount = CurrencyAmount(8, currency),
            uuid = UUID.randomUUID(),
            created = secondDate,
        )

    val expenses = listOf(coffeeExpense, lunchExpense, parkingExpense)

    val actual = transformer.transform(expenses)
    advanceUntilIdle()
    val expected: Map<LocalDate, List<Expense>> =
        mapOf(
            secondDate to listOf(coffeeExpense, parkingExpense),
            firstDate to listOf(lunchExpense),
        )

    assert(actual == expected)
  }
}
