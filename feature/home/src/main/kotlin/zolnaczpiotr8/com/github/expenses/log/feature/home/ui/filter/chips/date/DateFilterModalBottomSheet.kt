package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.semantics.selectableGroup
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateFilterModalBottomSheet(
    modifier: Modifier = Modifier,
    state: DateFilterSheetState = rememberDateFilterSheetState(DateFilter.Month),
    onYearClicked: () -> Unit = {},
    onCustomClicked: () -> Unit = {},
    onMonthClicked: () -> Unit = {},
    onAnyDateClicked: () -> Unit = {},
) {
    if (state.isHidden) {
        return
    }

    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = modifier.semantics {
            selectableGroup()
        },
        onDismissRequest = {
            scope.launch {
                state.hide()
            }
        },
        sheetState = state.internalSheetState,
        dragHandle = {
        },
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = IncrementalPaddings.x4,
                )
                .padding(
                    vertical = IncrementalPaddings.x3,
                ),
            text = stringResource(R.string.date_filter_title),
            style = MaterialTheme.typography.titleMedium,
        )
        val anySelected = state.filter is DateFilter.AnyDate
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .selectable(
                    selected = anySelected,
                    onClick = {
                        scope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            onAnyDateClicked()
                        }
                    },
                    role = Role.RadioButton,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.padding(
                    start = IncrementalPaddings.x4,
                ),
                selected = anySelected,
                onClick = null,
            )
            Text(
                text = stringResource(R.string.date_filter_any),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = IncrementalPaddings.x4,
                    vertical = IncrementalPaddings.x3,
                ),
            )
        }

        val isMonthSelected = state.filter is DateFilter.Month
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .selectable(
                    selected = isMonthSelected,
                    onClick = {
                        scope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            onMonthClicked()
                        }
                    },
                    role = Role.RadioButton,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.padding(
                    start = IncrementalPaddings.x4,
                ),
                selected = isMonthSelected,
                onClick = null,
            )
            Text(
                text = stringResource(R.string.date_filter_this_month),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = IncrementalPaddings.x4,
                    vertical = IncrementalPaddings.x3,
                ),
            )
        }

        val isYearSelected = state.filter is DateFilter.Year
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .selectable(
                    selected = isYearSelected,
                    onClick = {
                        scope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            onYearClicked()
                        }
                    },
                    role = Role.RadioButton,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.padding(
                    start = IncrementalPaddings.x4,
                ),
                selected = isYearSelected,
                onClick = null,
            )
            Text(
                text = stringResource(R.string.date_filter_this_year),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = IncrementalPaddings.x4,
                    vertical = IncrementalPaddings.x3,
                ),
            )
        }

        val isCustomSelected = state.filter is DateFilter.Custom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .selectable(
                    selected = isCustomSelected,
                    onClick = {
                        scope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            onCustomClicked()
                        }
                    },
                    role = Role.RadioButton,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.padding(
                    start = IncrementalPaddings.x4,
                ),
                selected = isCustomSelected,
                onClick = null,
            )
            Text(
                text = stringResource(R.string.date_filter_custom),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = IncrementalPaddings.x4,
                    vertical = IncrementalPaddings.x3,
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberDateFilterSheetState(
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

internal class DateFilterSheetState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    val internalSheetState: SheetState,
    val filter: DateFilter,
) {

    @OptIn(ExperimentalMaterial3Api::class)
    val isHidden: Boolean
        get() = internalSheetState.isVisible.not()

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun show() {
        internalSheetState.show()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun hide() {
        internalSheetState.hide()
    }
}
