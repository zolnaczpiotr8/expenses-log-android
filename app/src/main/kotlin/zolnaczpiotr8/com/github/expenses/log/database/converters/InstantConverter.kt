package zolnaczpiotr8.com.github.expenses.log.database.converters

import androidx.room.TypeConverter
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class InstantConverter {

    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun toInstant(
        milliSeconds: Long?,
    ): Instant? = milliSeconds?.let(Instant::fromEpochMilliseconds)

    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun fromInstant(
        instant: Instant?,
    ): Long? = instant?.let(Instant::toEpochMilliseconds)
}
