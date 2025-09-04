package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun DateFilterChipMonthPreview() {
  ExpensesLogTheme { DateFilterChip(filter = DateFilter.Month) }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun DateFilterChipYearPreview() {
  ExpensesLogTheme { DateFilterChip(filter = DateFilter.Year) }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun DateFilterChipAnyPreview() {
  ExpensesLogTheme { DateFilterChip(filter = DateFilter.Any) }
}
