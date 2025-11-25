package zolnaczpiotr8.com.github.expenses.log.data

import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.database.daos.ShowEmptyCategoriesDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.ShowEmptyCategoriesEntity

internal class ShowEmptyCategoriesRepository
@Inject
constructor(
    private val showEmptyCategoriesDao: ShowEmptyCategoriesDao,
) {

  fun showEmptyCategory(): Flow<Boolean> =
      showEmptyCategoriesDao.showEmptyCategories().map { it?.value ?: false }

  suspend fun set(
      value: Boolean,
  ) {
    try {
      showEmptyCategoriesDao.insert(
          ShowEmptyCategoriesEntity(
              value = value,
          ),
      )
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }
}
