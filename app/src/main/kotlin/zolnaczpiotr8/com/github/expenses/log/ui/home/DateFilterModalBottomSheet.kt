package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.toFormattedString
import zolnaczpiotr8.com.github.expenses.log.ui.components.Measurements

@Composable
fun DateFilterModalBottomSheet(
    modifier: Modifier = Modifier,
    state: DateFilterSheetState = rememberDateFilterSheetState(DateFilter.Month),
    onYearClicked: () -> Unit = {},
    onCustomClicked: () -> Unit = {},
    onMonthClicked: () -> Unit = {},
    onAnyDateClicked: () -> Unit = {},
) {
  val scope = rememberCoroutineScope()

  if (state.isHidden) {
    return
  }

  ModalBottomSheet(
      modifier = modifier,
      onDismissRequest = { scope.launch { state.hide() } },
      sheetState = state.internalSheetState,
  ) {
    Text(
        modifier =
            Modifier.padding(
                    start = Measurements.ListItem.startPadding,
                )
                .padding(
                    vertical = Measurements.ListItem.verticalPadding,
                ),
        text = stringResource(R.string.date_filter_title),
        style = MaterialTheme.typography.titleLarge,
    )

    Column(Modifier.selectableGroup()) {
      RadioButtonRow(
          isSelected = state.filter is DateFilter.Any,
          text = toFormattedString(DateFilter.Any),
          onClick = { scope.launch { state.hide() }.invokeOnCompletion { onAnyDateClicked() } },
      )

      RadioButtonRow(
          isSelected = state.filter is DateFilter.Month,
          text = toFormattedString(DateFilter.Month),
          onClick = { scope.launch { state.hide() }.invokeOnCompletion { onMonthClicked() } },
      )

      RadioButtonRow(
          isSelected = state.filter is DateFilter.Year,
          text = toFormattedString(DateFilter.Year),
          onClick = { scope.launch { state.hide() }.invokeOnCompletion { onYearClicked() } },
      )

      val isCustomSelected = state.filter is DateFilter.Custom

      RadioButtonRow(
          isSelected = isCustomSelected,
          text = stringResource(R.string.date_filter_custom),
          onClick = { scope.launch { state.hide() }.invokeOnCompletion { onCustomClicked() } },
      )
    }
  }
}

@Composable
fun RadioButtonRow(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
  val onClickLabel = stringResource(R.string.choose_action_label)
  Row(
      modifier =
          modifier.fillMaxWidth().wrapContentHeight().clearAndSetSemantics {
            contentDescription = text
            selected = isSelected
            role = Role.RadioButton
            onClick(
                label = onClickLabel,
                action = {
                  onClick()
                  true
                },
            )
          },
      verticalAlignment = Alignment.CenterVertically,
  ) {
    RadioButton(
        modifier =
            Modifier.padding(
                start = Measurements.ListItem.startPadding,
            ),
        selected = isSelected,
        onClick = onClick,
    )
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier =
            Modifier.padding(
                    vertical = Measurements.ListItem.verticalPadding,
                )
                .padding(start = Measurements.ListItem.startPadding),
    )
  }
}

@Composable
fun rememberDateFilterSheetState(
    filter: DateFilter = DateFilter.Month,
): DateFilterSheetState {
  val internalSheetState = rememberModalBottomSheetState()
  return remember(filter) {
    DateFilterSheetState(
        internalSheetState = internalSheetState,
        filter = filter,
    )
  }
}

class DateFilterSheetState
constructor(
    val internalSheetState: SheetState,
    val filter: DateFilter,
) {

  val isHidden: Boolean
    get() = internalSheetState.isVisible.not()

  suspend fun show() {
    internalSheetState.show()
  }

  suspend fun hide() {
    internalSheetState.hide()
  }
}
