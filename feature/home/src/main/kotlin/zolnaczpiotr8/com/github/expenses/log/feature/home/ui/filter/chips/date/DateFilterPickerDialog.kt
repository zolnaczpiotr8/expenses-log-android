package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.Instant
import zolnaczpiotr8.com.github.expenses.log.core.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateFilterPickerDialog(
    initial: DateFilter.Custom? = null,
    onDismiss: () -> Unit = {
    },
    onPicked: (DateFilter) -> Unit = {
    },
) {
    val state = rememberDateRangePickerState(
        initialSelectedStartDateMillis = initial?.start?.toEpochMilliseconds(),
        initialSelectedEndDateMillis = initial?.end?.toEpochMilliseconds(),
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
