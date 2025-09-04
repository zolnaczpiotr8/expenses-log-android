package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun NewExpenseTopBarPreview() {
  ExpensesLogTheme { NewExpenseTopBar(onGoBackClick = {}) }
}
