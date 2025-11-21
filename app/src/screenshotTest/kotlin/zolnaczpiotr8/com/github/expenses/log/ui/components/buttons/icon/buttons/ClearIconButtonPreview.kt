package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@ExpensesLogPreview
@PreviewTest
fun ClearIconButtonPreview() {
  ExpensesLogTheme { ClearIconButton(onClick = {}) }
}
