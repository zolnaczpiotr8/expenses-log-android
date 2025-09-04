package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.Instant
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

@Composable
fun DateFilterPickerDialog(
    initial: DateFilter.Custom? = null,
    onDismiss: () -> Unit = {},
    onPicked: (DateFilter) -> Unit = {},
) {
  val state =
      rememberDateRangePickerState(
          initialSelectedStartDateMillis = initial?.start?.toEpochMilli(),
          initialSelectedEndDateMillis = initial?.end?.toEpochMilli(),
      )
  DatePickerDialog(
      onDismissRequest = onDismiss,
      confirmButton = {
        TextButton(
            onClick = {
              val start =
                  state.selectedStartDateMillis?.let(Instant::ofEpochMilli)
                      ?: run {
                        onDismiss()
                        return@TextButton
                      }
              val end =
                  state.selectedEndDateMillis?.let(Instant::ofEpochMilli)
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
      dismissButton = { DialogDismissButton(onClick = onDismiss) },
  ) {
    DateRangePicker(
        state = state,
    )
  }
}
