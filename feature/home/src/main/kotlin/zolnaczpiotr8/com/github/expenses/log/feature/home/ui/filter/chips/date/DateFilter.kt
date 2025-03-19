package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.core.os.bundleOf
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

@Stable
sealed interface DateFilter {

    companion object {

        val default = Month
        private const val ID = "ID"
        private const val START = "START"
        private const val END = "END"

        fun fromBundle(
            bundle: Bundle,
        ): DateFilter = when (bundle.getLong(ID, Month.ID)) {
            AnyDate.ID -> AnyDate
            Year.ID -> Year
            Month.ID -> Month
            Custom.ID -> Custom(
                from = bundle.getLong(START, default.start.toEpochMilliseconds()),
                to = bundle.getLong(END, default.end.toEpochMilliseconds()),
            )
            else -> default
        }
    }

    val start: Instant
    val end: Instant
        get() = Clock.System.now()

    fun toBundle(): Bundle

    data object AnyDate : DateFilter {

        override val start: Instant = Instant.DISTANT_PAST

        override val end: Instant = Instant.DISTANT_FUTURE

        const val ID = 1L

        override fun toBundle(): Bundle = bundleOf(
            DateFilter.ID to ID,
        )
    }

    data object Month : DateFilter {

        override val start: Instant
            get() = with(Clock.System.now().toLocalDateTime(TimeZone.UTC)) {
                LocalDateTime(
                    year = year,
                    monthNumber = monthNumber,
                    dayOfMonth = 1,
                    hour = 0,
                    minute = 0,
                    second = 0,
                    nanosecond = 0,
                ).toInstant(TimeZone.UTC)
            }

        const val ID = 2L

        override fun toBundle(): Bundle = bundleOf(
            DateFilter.ID to ID,
        )
    }

    data object Year : DateFilter {

        override val start: Instant
            get() = with(Clock.System.now().toLocalDateTime(TimeZone.UTC)) {
                LocalDateTime(
                    year = year,
                    monthNumber = 1,
                    dayOfMonth = 1,
                    hour = 0,
                    minute = 0,
                    second = 0,
                    nanosecond = 0,
                ).toInstant(TimeZone.UTC)
            }

        const val ID = 4L

        override fun toBundle(): Bundle = bundleOf(
            DateFilter.ID to ID,
        )
    }

    data class Custom(
        override val start: Instant,
        override val end: Instant,
    ) : DateFilter {

        companion object {
            const val ID = 5L
        }

        constructor(
            from: Long,
            to: Long,
        ) : this(
            start = Instant.fromEpochMilliseconds(from),
            end = Instant.fromEpochMilliseconds(to),
        )

        override fun toBundle(): Bundle = bundleOf(
            DateFilter.ID to ID,
            START to start.toEpochMilliseconds(),
            END to end.toEpochMilliseconds(),
        )
    }
}
