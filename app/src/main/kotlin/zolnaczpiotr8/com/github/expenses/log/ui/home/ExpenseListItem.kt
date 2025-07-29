package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.IconButtonWithTooltip

@Composable
fun ExpenseListItem(
    modifier: Modifier = Modifier,
    expense: ExpenseItem.Expense,
    onDeleteClick: () -> Boolean = { true },
) {
  val deleteLabel = stringResource(R.string.delete_action_label)
  val totalAmount = remember(expense.amount) { expense.amount.toFormattedString() }
  ListItem(
      modifier =
          modifier.semantics {
            onClick(
                label = deleteLabel,
                action = {
                  onDeleteClick()
                  true
                },
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
            text = totalAmount,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
      },
      supportingContent = {
        expense.title
            ?.takeIf { it.isNotBlank() }
            ?.let {
              Text(
                  text = it,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
              )
            }
      },
      trailingContent = {
        IconButtonWithTooltip(
            modifier = Modifier.clearAndSetSemantics {},
            onClick = { onDeleteClick() },
            imageVector = Icons.Default.Delete,
            label = deleteLabel,
        )
      },
  )
}
