package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@Composable
internal fun DateFilterChip(
    modifier: Modifier = Modifier,
    filter: DateFilter = DateFilter.Month,
    onClick: () -> Unit = {
    },
) {
    val isSelected = filter != DateFilter.AnyDate
    val labelResource = when (filter) {
        is DateFilter.AnyDate -> R.string.date_filter_title
        is DateFilter.Month -> R.string.date_filter_this_month
        is DateFilter.Custom -> R.string.date_filter_custom
        is DateFilter.Year -> R.string.date_filter_this_year
    }
    FilterChip(
        modifier = modifier.semantics {
            role = Role.DropdownList
        },
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(stringResource(labelResource))
        },
        leadingIcon = {
            if (isSelected) {
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
