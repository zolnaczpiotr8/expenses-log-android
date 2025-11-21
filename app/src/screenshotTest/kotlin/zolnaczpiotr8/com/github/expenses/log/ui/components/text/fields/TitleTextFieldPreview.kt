package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun TitleTextFieldNotEmptyPreview() {
  ExpensesLogTheme { TitleTextField(state = rememberTitleTextFieldState(title = "Test")) }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun TitleTextFieldEmptyPreview() {
  ExpensesLogTheme { TitleTextField() }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun TitleTextFieldErrorPreview() {
  ExpensesLogTheme { TitleTextField(state = rememberTitleTextFieldState(isError = true)) }
}
