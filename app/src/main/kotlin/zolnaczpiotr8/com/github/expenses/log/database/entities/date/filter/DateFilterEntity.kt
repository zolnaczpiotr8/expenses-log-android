package zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Entity(
    tableName = "date_filter",
)
data class DateFilterEntity
@OptIn(ExperimentalTime::class)
constructor(
    @PrimaryKey val id: Int = 1,
    val title: String,
    val start: Instant? = null,
    val finish: Instant? = null,
)
