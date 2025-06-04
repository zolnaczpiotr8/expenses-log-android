package zolnaczpiotr8.com.github.expenses.log.core.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.ANY_DATE_FILTER
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.CUSTOM_DATE_FILTER
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.MONTH_DATE_FILTER
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.YEAR_DATE_FILTER
import zolnaczpiotr8.com.github.expenses.log.core.model.DateFilter
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
            title = ANY_DATE_FILTER,
        )
        is DateFilter.Month -> DateFilterEntity(
            title = MONTH_DATE_FILTER,
        )
        is DateFilter.Year -> DateFilterEntity(
            title = YEAR_DATE_FILTER,
        )
        is DateFilter.Custom -> DateFilterEntity(
            title = CUSTOM_DATE_FILTER,
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
            ANY_DATE_FILTER -> DateFilter.Any
            MONTH_DATE_FILTER -> DateFilter.Month
            YEAR_DATE_FILTER -> DateFilter.Year
            CUSTOM_DATE_FILTER -> {
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
