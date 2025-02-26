package zolnaczpiotr8.com.github.expenses.log.core.database.converters

import androidx.room.TypeConverter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class UuidConverter {

    @OptIn(ExperimentalUuidApi::class)
    @TypeConverter
    fun toUuid(string: String?): Uuid? = string?.let(Uuid::parseHex)

    @OptIn(ExperimentalUuidApi::class)
    @TypeConverter
    fun fromUuid(uuid: Uuid?): String? = uuid?.toHexString()
}
