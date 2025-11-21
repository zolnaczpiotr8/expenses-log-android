package zolnaczpiotr8.com.github.expenses.log.database.converters

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverter {

  @TypeConverter
  fun toInstant(
      seconds: Long?,
  ): Instant? = seconds?.let(Instant::ofEpochSecond)

  @TypeConverter
  fun fromInstant(
      instant: Instant?,
  ): Long? = instant?.epochSecond
}
