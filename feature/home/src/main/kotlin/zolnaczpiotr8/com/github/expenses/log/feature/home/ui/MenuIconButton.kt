package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.buttons.icon.buttons.IconButtonWithTooltip
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
fun MenuIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButtonWithTooltip(
        modifier = modifier,
        imageVector = Icons.Default.MoreVert,
        label = stringResource(coreUiR.string.menu_label),
        onClick = onClick,
        onClickLabel = stringResource(R.string.show_action_label),
    )
}
