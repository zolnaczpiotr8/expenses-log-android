package zolnaczpiotr8.com.github.expenses.log.feature.add.expense.save.fab

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.add.expense.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SaveFabTooltip(content: @Composable () -> Unit) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(
                    stringResource(R.string.save_fab_description),
                )
            }
        },
        state = rememberTooltipState(),
        content = content,
    )
}
