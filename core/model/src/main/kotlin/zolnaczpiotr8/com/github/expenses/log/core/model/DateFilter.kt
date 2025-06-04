package zolnaczpiotr8.com.github.expenses.log.core.model

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface DateFilter {

    companion object {
        val Default = Month
    }

    data object Any : DateFilter

    data object Year : DateFilter

    data object Month : DateFilter

    data class Custom(
        val start: Instant,
        val end: Instant,
    ) : DateFilter {

        override fun toString(): String {
            val timeZone = TimeZone.currentSystemDefault()
            val start = start.toLocalDateTime(timeZone).date
            val end = end.toLocalDateTime(timeZone).date
            return "$start - $end"
        }
    }
}
