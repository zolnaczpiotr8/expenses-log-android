package zolnaczpiotr8.com.github.expenses.log.model

import androidx.compose.runtime.Stable
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Stable
sealed interface DateFilter {

  companion object {
    val Default = Month
  }

  data object Any : DateFilter {
    const val NAME: String = "Any"
  }

  data object Year : DateFilter {
    const val NAME: String = "Year"
  }

  data object Month : DateFilter {
    const val NAME: String = "Month"
  }

  data class Custom(
      val start: Instant,
      val end: Instant,
  ) : DateFilter {

    companion object {
      const val NAME: String = "Custom"
    }

    override fun toString(): String {
      val timeZone = TimeZone.currentSystemDefault()
      val start = start.toLocalDateTime(timeZone).date
      val end = end.toLocalDateTime(timeZone).date
      return "$start - $end"
    }
  }
}
