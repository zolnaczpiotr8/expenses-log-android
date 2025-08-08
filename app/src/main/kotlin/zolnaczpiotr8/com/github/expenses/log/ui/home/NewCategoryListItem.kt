package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun NewCategoryListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
  ListItem(
      modifier =
          modifier.clickable(
              onClick = onClick,
              onClickLabel = stringResource(R.string.add_action_label),
          ),
      leadingContent = {
        Icon(
            imageVector = Icons.Default.Category,
            contentDescription = null,
        )
      },
      headlineContent = {
        Text(
            text = stringResource(R.string.new_category),
        )
      },
  )
}
