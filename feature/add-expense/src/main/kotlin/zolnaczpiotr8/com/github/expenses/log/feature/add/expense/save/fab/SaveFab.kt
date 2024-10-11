package zolnaczpiotr8.com.github.expenses.log.feature.add.expense.save.fab

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.plain.tooltip.PlainTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.add.expense.R

@Composable
internal fun SaveFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val description = stringResource(R.string.save_fab_description)
    PlainTooltip(
        supportingText = description,
        interactionSource = interactionSource,
    ) {
        FloatingActionButton(
            interactionSource = interactionSource,
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = description,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        SaveFab(
            onClick = {
            },
        )
    }
}
