package zolnaczpiotr8.com.github.expenses.log.database.converters

import androidx.room.TypeConverter
import java.util.UUID

class UuidConverter {

  @TypeConverter
  fun toUuid(
      uuid: String?,
  ): UUID? = uuid?.let(UUID::fromString)

  @TypeConverter
  fun fromUuid(
      uuid: UUID?,
  ): String? = uuid?.let(UUID::toString)
}
