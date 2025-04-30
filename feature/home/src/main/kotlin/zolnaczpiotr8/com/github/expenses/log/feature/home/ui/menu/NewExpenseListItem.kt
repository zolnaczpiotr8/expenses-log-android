package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
fun NewExpenseListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = modifier.clickable(
            onClick = onClick,
            onClickLabel = stringResource(R.string.add_action_label),
        ).focusable(),
        leadingContent = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
            )
        },
        headlineContent = {
            Text(
                text = stringResource(coreUiR.string.new_expense),
            )
        },
    )
}
