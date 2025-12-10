package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun FirstLaunchAgreementDialog(onTermsOfServiceClick: () -> Unit = {}, onAgreeClick: () -> Unit) {
  AlertDialog(
      onDismissRequest = {},
      icon = {
        Icon(
            imageVector = Icons.Default.Description,
            contentDescription = null,
        )
      },
      title = { Text(text = stringResource(R.string.dialog_first_launch_agree_title)) },
      properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
      confirmButton = {
        TextButton(
            onClick = onAgreeClick,
        ) {
          Text(stringResource(R.string.agree_to_terms))
        }
      },
      text = { Text(text = stringResource(R.string.dialog_first_launch_terms_message)) },
      dismissButton = {
        TextButton(onClick = onTermsOfServiceClick) {
          Text(stringResource(R.string.terms_of_service))
        }
      },
  )
}
