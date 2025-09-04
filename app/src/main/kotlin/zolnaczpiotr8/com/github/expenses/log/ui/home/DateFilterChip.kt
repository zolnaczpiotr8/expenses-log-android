package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.toFormattedString

@Composable
fun DateFilterChip(
    modifier: Modifier = Modifier,
    filter: DateFilter = DateFilter.Month,
    onClick: () -> Unit = {},
) {
  val isSelected = filter != DateFilter.Any
  FilterChip(
      modifier = modifier.semantics { role = Role.DropdownList },
      selected = isSelected,
      onClick = onClick,
      label = {
        Text(
            text = toFormattedString(filter = filter),
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
      trailingIcon = {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.size(FilterChipDefaults.IconSize),
        )
      },
  )
}
