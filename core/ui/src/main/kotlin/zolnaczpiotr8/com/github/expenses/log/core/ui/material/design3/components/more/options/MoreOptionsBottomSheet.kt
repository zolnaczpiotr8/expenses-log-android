package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreOptionsBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val description = stringResource(R.string.more_options_description)
    ModalBottomSheet(
        modifier =
        modifier
            .semantics {
                contentDescription = description
            }
            .focusable()
            .clickable(
                onClickLabel =
                stringResource(
                    R.string.more_options_bottom_sheet_on_click_label,
                ),
                onClick = onClick,
            ),
        dragHandle = null,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        MoreOptionsBottomSheet(
            onClick = {
            },
            onDismissRequest = {
            },
            sheetState =
            SheetState(
                false,
                LocalDensity.current,
                SheetValue.PartiallyExpanded,
                {
                    true
                },
                false,
            ),
            content = {
                OptionListItem(
                    onClick = {
                    },
                    headline = "Settings",
                    leadingIcon = Icons.Default.Settings,
                )
            },
        )
    }
}
