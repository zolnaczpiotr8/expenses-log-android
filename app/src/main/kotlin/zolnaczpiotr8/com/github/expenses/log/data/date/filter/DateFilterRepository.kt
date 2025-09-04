package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.database.daos.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterRepository
@Inject
constructor(
    private val dateFilterDao: DateFilterDao,
    private val dateFilterMapper: DateFilterMapper,
    private val dateFilterEntityMapper: DateFilterEntityMapper,
) {

  suspend fun update(
      dateFilter: DateFilter,
  ) {
    try {
      dateFilterDao.insert(dateFilterMapper.toEntity(dateFilter))
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }

  fun dateFilter(): Flow<DateFilter> = dateFilterDao.dateFilter().map(dateFilterEntityMapper::map)
}
