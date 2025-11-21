package zolnaczpiotr8.com.github.expenses.log.data.categories

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import java.util.UUID
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category

class CategoryAggregatorTest {

  private val currency = Currency.getInstance(ULocale.getDefault())

  @Test
  fun whenNoCategories_thenZero() = runTest {
    val aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler))

    val actual = aggregator.aggregateInCurrency(categories = emptyList(), currency = currency)
    advanceUntilIdle()
    val expected =
        CategoriesSummary(totalAmount = CurrencyAmount(0, currency), categories = emptyList())

    assert(actual == expected)
  }

  @Test
  fun whenOneCategory_thenOne() = runTest {
    val aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler))

    val categoryEntity =
        CategoryTotalEntity(uuid = UUID.randomUUID(), title = "title", totalAmount = 100.0)
    val actual =
        aggregator.aggregateInCurrency(categories = listOf(categoryEntity), currency = currency)
    advanceUntilIdle()
    val expected =
        CategoriesSummary(
            totalAmount = CurrencyAmount(categoryEntity.totalAmount, currency),
            categories =
                listOf(
                    Category(
                        uuid = categoryEntity.uuid,
                        totalAmount = CurrencyAmount(categoryEntity.totalAmount, currency),
                        title = categoryEntity.title,
                    )
                ),
        )

    assert(actual == expected)
  }

  @Test
  fun whenMultipleCategories_thenOrderPreservedAndSumCorrectly() = runTest {
    val aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler))

    val categoryEntity1 =
        CategoryTotalEntity(uuid = UUID.randomUUID(), title = "title", totalAmount = 100.0)
    val categoryEntity2 =
        CategoryTotalEntity(uuid = UUID.randomUUID(), title = "title2", totalAmount = 52.5)
    val categoryEntity3 =
        CategoryTotalEntity(uuid = UUID.randomUUID(), title = "title3", totalAmount = 233.0)
    val actual =
        aggregator.aggregateInCurrency(
            categories = listOf(categoryEntity1, categoryEntity2, categoryEntity3),
            currency = currency,
        )
    advanceUntilIdle()
    val expected =
        CategoriesSummary(
            totalAmount =
                CurrencyAmount(
                    categoryEntity1.totalAmount +
                        categoryEntity2.totalAmount +
                        categoryEntity3.totalAmount,
                    currency,
                ),
            categories =
                listOf(
                    Category(
                        uuid = categoryEntity1.uuid,
                        totalAmount = CurrencyAmount(categoryEntity1.totalAmount, currency),
                        title = categoryEntity1.title,
                    ),
                    Category(
                        uuid = categoryEntity2.uuid,
                        totalAmount = CurrencyAmount(categoryEntity2.totalAmount, currency),
                        title = categoryEntity2.title,
                    ),
                    Category(
                        uuid = categoryEntity3.uuid,
                        totalAmount = CurrencyAmount(categoryEntity3.totalAmount, currency),
                        title = categoryEntity3.title,
                    ),
                ),
        )

    assert(actual == expected)
  }
}
