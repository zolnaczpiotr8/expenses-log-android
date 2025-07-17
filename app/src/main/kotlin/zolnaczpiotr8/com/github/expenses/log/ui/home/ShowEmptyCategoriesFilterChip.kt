package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R

@Composable
fun ShowEmptyCategoriesFilterChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean = true,
    onClick: () -> Unit = {},
) {
  FilterChip(
      modifier = modifier,
      selected = isSelected,
      onClick = onClick,
      label = {
        Text(
            text = stringResource(R.string.show_empty_categories_label),
            maxLines = 1,
        )
      },
      leadingIcon = {
        AnimatedVisibility(isSelected) {
          Icon(
              imageVector = Icons.Default.Done,
              contentDescription = null,
              modifier = Modifier.size(FilterChipDefaults.IconSize),
          )
        }
      },
  )
}
