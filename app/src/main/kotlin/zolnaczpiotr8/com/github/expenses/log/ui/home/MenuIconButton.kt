package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.IconButtonWithTooltip

@Composable
fun MenuIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
  IconButtonWithTooltip(
      modifier = modifier,
      imageVector = Icons.Default.MoreVert,
      label = stringResource(R.string.menu_label),
      onClick = onClick,
      onClickLabel = stringResource(R.string.show_action_label),
  )
}
