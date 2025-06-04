package zolnaczpiotr8.com.github.expenses.log.core.database.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {

    @TypeConverter
    fun toInstant(
        milliSeconds: Long?,
    ): Instant? = milliSeconds?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun fromInstant(
        instant: Instant?,
    ): Long? = instant?.let(Instant::toEpochMilliseconds)
}
