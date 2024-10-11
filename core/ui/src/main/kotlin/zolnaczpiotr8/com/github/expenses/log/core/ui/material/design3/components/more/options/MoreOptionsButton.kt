package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.plain.tooltip.PlainTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun MoreOptionsButton(onClick: () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val description = stringResource(R.string.more_options_button_description)
    PlainTooltip(
        supportingText = description,
        interactionSource = interactionSource,
    ) {
        IconButton(
            onClick = onClick,
            interactionSource = interactionSource,
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = description,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        MoreOptionsButton(
            onClick = {
            },
        )
    }
}
