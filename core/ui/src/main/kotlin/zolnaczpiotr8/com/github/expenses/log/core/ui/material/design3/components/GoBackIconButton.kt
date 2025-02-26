package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

@Composable
fun GoBackIconButton(
    onClick: () -> Unit = {
    },
) {
    val description = stringResource(R.string.go_back_button_description)
    val interactionSource = remember {
        MutableInteractionSource()
    }
    PlainTooltip(
        supportingText = description,
        interactionSource = interactionSource,
    ) {
        IconButton(
            onClick = onClick,
            interactionSource = interactionSource,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = description,
            )
        }
    }
}
