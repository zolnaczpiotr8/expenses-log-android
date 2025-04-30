package zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

const val ANY_DATE_FILTER = "Any"
const val MONTH_DATE_FILTER = "Month"
const val YEAR_DATE_FILTER = "Year"
const val CUSTOM_DATE_FILTER = "Custom"

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
