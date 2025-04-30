package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
fun DeleteDialogConfirmButton(
    onClick: () -> Unit = {
    },
) {
    TextButton(onClick = onClick) {
        Text(stringResource(R.string.delete_action_label))
    }
}
