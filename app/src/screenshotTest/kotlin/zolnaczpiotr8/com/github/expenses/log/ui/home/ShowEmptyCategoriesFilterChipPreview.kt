package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun ShowEmptyCategoriesFilterChipSelectedPreview() {
  ExpensesLogTheme { ShowEmptyCategoriesFilterChip(isSelected = true) }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun ShowEmptyCategoriesFilterChipNotSelectedPreview() {
  ExpensesLogTheme { ShowEmptyCategoriesFilterChip(isSelected = false) }
}
