package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.extended.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun SaveExtendedFab(
    expanded: Boolean = false,
    onClick: () -> Unit = {},
) {
  ExtendedFloatingActionButton(
      expanded = expanded,
      onClick = onClick,
      text = { Text(stringResource(R.string.save_action_label)) },
      icon = {
        Icon(
            imageVector = Icons.Default.Save,
            contentDescription = null,
        )
      },
  )
}
