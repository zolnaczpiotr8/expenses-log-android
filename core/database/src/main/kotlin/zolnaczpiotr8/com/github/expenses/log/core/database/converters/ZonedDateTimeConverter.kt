package zolnaczpiotr8.com.github.expenses.log.core.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME.withLocale(Locale.US)

@ProvidedTypeConverter
class ZonedDateTimeConverter {
    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime?): String? = zonedDateTime?.run(FORMATTER::format)

    @TypeConverter
    fun toZonedDateTime(string: String?): ZonedDateTime? =
        if (string == null) {
            null
        } else {
            try {
                ZonedDateTime.parse(string, FORMATTER)
            } catch (error: Throwable) {
                throw IllegalArgumentException(error.message)
            }
        }
}
