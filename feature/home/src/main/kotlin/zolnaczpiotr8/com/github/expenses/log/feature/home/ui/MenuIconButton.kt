package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.PlainTooltip
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
internal fun MenuIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val description = stringResource(coreUiR.string.menu_label)
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val onClickLabel = stringResource(R.string.show_action_label)
    PlainTooltip(
        modifier = modifier,
        supportingText = description,
        interactionSource = interactionSource,
    ) {
        IconButton(
            modifier = Modifier.semantics {
                onClick(
                    label = onClickLabel,
                    action = null,
                )
            },
            interactionSource = interactionSource,
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = description,
            )
        }
    }
}
