package zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options.OptionListItem
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.R

@Composable
internal fun ShowStatisticsListItem(onClick: () -> Unit) {
    OptionListItem(
        onClick = onClick,
        headline = stringResource(R.string.show_statistics_option_description),
        leadingIcon = Icons.Default.BarChart,
    )
}
