package zolnaczpiotr8.com.github.expenses.log.model

import android.text.format.DateUtils
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import java.time.Instant
import zolnaczpiotr8.com.github.expenses.log.R

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
  }
}

@Composable
fun toFormattedString(filter: DateFilter): String =
    when (filter) {
      is DateFilter.Any -> stringResource(R.string.date_filter_any)
      is DateFilter.Month -> stringResource(R.string.date_filter_this_month)
      is DateFilter.Custom -> {
        val context = LocalContext.current
        remember(filter.start, filter.end) {
          DateUtils.formatDateRange(
              context,
              filter.start.toEpochMilli(),
              filter.end.toEpochMilli(),
              0,
          )
        }
      }

      is DateFilter.Year -> stringResource(R.string.date_filter_this_year)
    }
