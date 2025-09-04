package zolnaczpiotr8.com.github.expenses.log.ui.policies

import android.icu.text.DateFormat
import android.icu.util.ULocale
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.Instant
import java.util.Date

// Friday, August 1, 2025 12:00:00 AM GMT+00:00
private const val EPOCH_MILLISECONDS = 1_754_006_400_000

@Composable
fun rememberEffectiveDate(locale: ULocale = ULocale.getDefault()): String =
    remember(locale) {
      val date = Date.from(Instant.ofEpochMilli(EPOCH_MILLISECONDS))
      DateFormat.getDateInstance(DateFormat.LONG, locale)
          .format(
              date,
          )
    }
