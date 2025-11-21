package zolnaczpiotr8.com.github.expenses.log.data.categories

import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary

class CategoriesRepository
@Inject
constructor(
    private val settingsRepository: SettingsRepository,
    private val categoryDao: CategoryDao,
    private val aggregator: CategoryAggregator,
) {

  suspend fun delete(
      uuid: UUID,
  ) {
    try {
      categoryDao.delete(uuid)
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }

  suspend fun create(title: String, uuid: UUID = UUID.randomUUID()) {
    try {
      categoryDao.insert(
          CategoryEntity(
              uuid = uuid,
              title = title,
          ),
      )
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }

  fun categoriesTitles(): Flow<List<String>> = categoryDao.categoriesTitles()

  fun categories(): Flow<CategoriesSummary> =
      categoryDao
          .categories()
          .combine(
              flow = settingsRepository.currencyOrDefault,
              transform = aggregator::aggregateInCurrency,
          )
}
