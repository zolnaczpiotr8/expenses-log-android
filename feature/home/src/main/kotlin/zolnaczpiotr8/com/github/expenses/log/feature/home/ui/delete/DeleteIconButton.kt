package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.PlainTooltip
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
internal fun DeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {
    },
) {
    val description = stringResource(R.string.delete_action_label)
    val interactionSource = remember {
        MutableInteractionSource()
    }
    PlainTooltip(
        supportingText = description,
        interactionSource = interactionSource,
    ) {
        IconButton(
            modifier = modifier,
            interactionSource = interactionSource,
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = description,
            )
        }
    }
}
