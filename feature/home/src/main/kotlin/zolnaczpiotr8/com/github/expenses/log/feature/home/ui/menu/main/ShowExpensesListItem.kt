package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
internal fun ShowExpensesListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = modifier.clickable(
            onClick = onClick,
        ).focusable(),
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Visibility,
                contentDescription = null,
            )
        },
        headlineContent = {
            Text(
                text = stringResource(R.string.show_expenses_action_label),
            )
        },
    )
}
