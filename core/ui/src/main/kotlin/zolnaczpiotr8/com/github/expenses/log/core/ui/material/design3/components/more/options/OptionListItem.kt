package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun OptionListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    headline: String,
    leadingIcon: ImageVector,
) {
    ListItem(
        modifier =
        modifier
            .semantics {
                role = Role.Button
            }
            .focusable()
            .clickable {
                onClick.invoke()
            },
        leadingContent = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
            )
        },
        headlineContent = {
            Text(headline)
        },
    )
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        OptionListItem(
            onClick = {
            },
            headline = "Settings",
            leadingIcon = Icons.Default.Settings,
        )
    }
}
