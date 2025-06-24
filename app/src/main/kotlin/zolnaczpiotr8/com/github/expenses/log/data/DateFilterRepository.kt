package zolnaczpiotr8.com.github.expenses.log.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.database.daos.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

private val TAG = DateFilterRepository::class.java.name

class DateFilterRepository @Inject constructor(
    private val dateFilterDao: DateFilterDao,
) {

    suspend fun update(
        dateFilter: DateFilter,
    ) {
        try {
            dateFilterDao.insert(toEntity(dateFilter))
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (throwable: Throwable) {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    throwable.message,
                    throwable,
                )
            }
        }
    }

    private fun toEntity(
        dateFilter: DateFilter,
    ): DateFilterEntity = when (dateFilter) {
        is DateFilter.Any -> DateFilterEntity(
            title = DateFilter.Any.NAME,
        )
        is DateFilter.Month -> DateFilterEntity(
            title = DateFilter.Month.NAME,
        )
        is DateFilter.Year -> DateFilterEntity(
            title = DateFilter.Year.NAME,
        )
        is DateFilter.Custom -> DateFilterEntity(
            title = DateFilter.Custom.NAME,
            start = dateFilter.start,
            finish = dateFilter.end,
        )
    }

    fun dateFilter(): Flow<DateFilter> = dateFilterDao.dateFilter()
        .map {
            it?.let(::fromEntity) ?: DateFilter.Default
        }

    private fun fromEntity(
        entity: DateFilterEntity,
    ): DateFilter = with(entity) {
        when (title) {
            DateFilter.Any.NAME -> DateFilter.Any
            DateFilter.Month.NAME -> DateFilter.Month
            DateFilter.Year.NAME -> DateFilter.Year
            DateFilter.Custom.NAME -> {
                val start = start ?: return DateFilter.Default
                val end = finish ?: return DateFilter.Default
                return DateFilter.Custom(
                    start = start,
                    end = end,
                )
            }
            else -> DateFilter.Default
        }
    }
}
