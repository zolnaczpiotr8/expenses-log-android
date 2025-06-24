package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun GoBackIconButton(
    onClick: () -> Unit = {
    },
) {
    IconButtonWithTooltip(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        label = stringResource(R.string.go_back_button_description),
        onClick = onClick,
    )
}
