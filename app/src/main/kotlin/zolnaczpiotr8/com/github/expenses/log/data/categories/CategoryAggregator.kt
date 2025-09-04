package zolnaczpiotr8.com.github.expenses.log.data.categories

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category

class CategoryAggregator
@Inject
constructor(private val dispatcher: CoroutineContext = Dispatchers.Default) {

  suspend fun aggregateInCurrency(
      categories: List<CategoryTotalEntity>,
      currency: Currency,
  ): CategoriesSummary =
      withContext(dispatcher) {
        CategoriesSummary(
            totalAmount =
                CurrencyAmount(
                    categories.sumOf(CategoryTotalEntity::totalAmount),
                    currency,
                ),
            categories =
                categories.map {
                  Category(
                      uuid = it.uuid,
                      title = it.title,
                      totalAmount =
                          CurrencyAmount(
                              it.totalAmount,
                              currency,
                          ),
                  )
                },
        )
      }
}
