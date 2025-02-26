package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.expense

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.MenuIconButton

@Composable
internal fun ExpenseListItem(
    modifier: Modifier = Modifier,
    expense: Expense,
    onMenuClick: () -> Unit = {
    },
    onDeleteClick: () -> Boolean = {
        true
    },
    onEditClick: () -> Boolean = {
        true
    },
    onDetailsClick: () -> Boolean = {
        true
    },
) {
    val deleteLabel = stringResource(R.string.delete_action_label)
    val editLabel = stringResource(R.string.edit_action_label)
    val detailsLabel = stringResource(R.string.details_action_label)
    ListItem(
        modifier = modifier
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = detailsLabel,
                        action = onDetailsClick,
                    ),
                    CustomAccessibilityAction(
                        label = deleteLabel,
                        action = onDeleteClick,
                    ),
                    CustomAccessibilityAction(
                        label = editLabel,
                        action = onEditClick,
                    ),
                )
            },
        overlineContent = {
            Text(
                text = expense.categoryTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        headlineContent = {
            Text(
                text = expense.amount.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        supportingContent = {
            expense.title
                ?.takeIf {
                    it.isNotBlank()
                }?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
        },
        trailingContent = {
            MenuIconButton(
                modifier = Modifier
                    .clearAndSetSemantics {
                    },
                onClick = onMenuClick,
            )
        },
    )
}
