package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
fun DeleteDialogDismissButton(
    onClick: () -> Unit = {
    },
) {
    TextButton(
        onClick = onClick,
    ) {
        Text(stringResource(R.string.cancel_action_label))
    }
}
