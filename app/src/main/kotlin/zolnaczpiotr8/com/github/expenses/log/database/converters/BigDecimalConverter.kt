package zolnaczpiotr8.com.github.expenses.log.database.converters

import android.icu.math.BigDecimal
import androidx.room.TypeConverter

class BigDecimalConverter {

  @TypeConverter
  fun toBigDecimal(
      double: Double?,
  ): BigDecimal? = double?.let(::BigDecimal)

  @TypeConverter
  fun fromBigDecimal(
      bigDecimal: BigDecimal?,
  ): Double? = bigDecimal?.toDouble()
}
