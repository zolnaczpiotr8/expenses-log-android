package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
internal fun DeleteDialog(
    isVisible: Boolean = false,
    onHide: () -> Unit = {
    },
    onDeleteClick: () -> Unit = {
    },
    text: String = "",
) {
    if (isVisible.not()) {
        return
    }
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
            )
        },
        title = {
            Text(
                stringResource(R.string.delete_dialog_title),
            )
        },
        text = {
            Text(
                text,
            )
        },
        onDismissRequest = onHide,
        confirmButton = {
            DeleteDialogConfirmButton(
                onClick = {
                    onDeleteClick()
                    onHide()
                },
            )
        },
        dismissButton = {
            DeleteDialogDismissButton(
                onClick = onHide,
            )
        },
    )
}
