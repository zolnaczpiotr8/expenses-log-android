package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun ClearIconButton(
    onClick: () -> Unit,
) {
  IconButtonWithTooltip(
      onClick = onClick,
      imageVector = Icons.Default.Clear,
      label = stringResource(R.string.clear_action_label),
  )
}
