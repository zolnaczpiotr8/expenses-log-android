package zolnaczpiotr8.com.github.expenses.log.core.database.converters

import android.icu.math.BigDecimal
import androidx.room.TypeConverter

internal class BigDecimalConverter {

    @TypeConverter
    fun toBigDecimal(
        string: String?,
    ): BigDecimal? = string?.let(::BigDecimal)

    @TypeConverter
    fun fromBigDecimal(
        bigDecimal: BigDecimal?,
    ): String? = bigDecimal?.toString()
}
