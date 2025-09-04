package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

private const val TEXT = "Test"

@Composable
@PreviewTest
@ExpensesLogPreview
fun RadioButtonRowNotSelectedPreview() {
  ExpensesLogTheme { RadioButtonRow(isSelected = false, text = TEXT, onClick = {}) }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun RadioButtonRowSelectedPreview() {
  ExpensesLogTheme { RadioButtonRow(isSelected = true, text = TEXT, onClick = {}) }
}
