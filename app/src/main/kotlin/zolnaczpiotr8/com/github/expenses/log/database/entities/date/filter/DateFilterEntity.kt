package zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "date_filter",
)
data class DateFilterEntity(
    @PrimaryKey
    val id: Int = 1,
    val title: String,
    val start: Instant? = null,
    val finish: Instant? = null,
)
