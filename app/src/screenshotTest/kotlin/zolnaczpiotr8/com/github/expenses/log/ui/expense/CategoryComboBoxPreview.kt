package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@ExpensesLogPreview
@PreviewTest
fun CategoryComboBoxEmptyPreview() {
  ExpensesLogTheme { CategoryComboBox() }
}

@Composable
@ExpensesLogPreview
@PreviewTest
fun CategoryComboBoxNotEmptyPreview() {
  ExpensesLogTheme { CategoryComboBox(state = rememberCategoryComboBoxState(category = "Test")) }
}

@Composable
@ExpensesLogPreview
@PreviewTest
fun CategoryComboBoxErrorPreview() {
  ExpensesLogTheme { CategoryComboBox(state = rememberCategoryComboBoxState(isError = true)) }
}

@Composable
@ExpensesLogPreview
@PreviewTest
fun CategoryComboBoxEmptyExpandedPreview() {
  ExpensesLogTheme { CategoryComboBox(state = rememberCategoryComboBoxState(isExpanded = true)) }
}
