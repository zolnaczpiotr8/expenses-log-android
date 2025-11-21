package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun DialogDismissButton(
    onClick: () -> Unit = {},
) {
  TextButton(
      onClick = onClick,
  ) {
    Text(stringResource(R.string.cancel_action_label))
  }
}
