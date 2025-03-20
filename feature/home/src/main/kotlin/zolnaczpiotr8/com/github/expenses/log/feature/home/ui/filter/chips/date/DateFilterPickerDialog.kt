package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateFilterPickerDialog(
    initial: DateFilter? = null,
    onDismiss: () -> Unit = {
    },
    onPicked: (DateFilter.Custom) -> Unit = {
    },
) {
    val now = Clock.System.now()
    val state = rememberDateRangePickerState(
        initialSelectedStartDateMillis = initial?.start?.toEpochMilliseconds(),
        initialSelectedEndDateMillis = initial?.end?.toEpochMilliseconds(),
        initialDisplayedMonthMillis = initial?.end?.toEpochMilliseconds() ?: now.toEpochMilliseconds(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean = (utcTimeMillis <= now.toEpochMilliseconds())
        },
        yearRange = IntRange(
            start = 2025,
            endInclusive = now.toLocalDateTime(TimeZone.UTC).year,
        ),
    )
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val start = state.selectedStartDateMillis
                        ?.let(Instant::fromEpochMilliseconds)
                        ?: run {
                            onDismiss()
                            return@TextButton
                        }
                    val end = state.selectedEndDateMillis
                        ?.let(Instant::fromEpochMilliseconds)
                        ?: run {
                            onDismiss()
                            return@TextButton
                        }
                    onPicked(
                        DateFilter.Custom(
                            start = start,
                            end = end,
                        ),
                    )
                },
            ) {
                Text(stringResource(R.string.confirm_action_label))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(stringResource(R.string.cancel_action_label))
            }
        },
    ) {
        DateRangePicker(
            state = state,
        )
    }
}
