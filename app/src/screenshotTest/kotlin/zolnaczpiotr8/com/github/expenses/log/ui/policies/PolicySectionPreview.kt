package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun PolicySectionNoFooterPreview() {
  ExpensesLogTheme {
    PolicySection(
        title = "Title",
        body =
            "Body body body body body body body body body body body body body body body body body body body body body body body body body body body ",
    )
  }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun PolicySectionFooterPreview() {
  ExpensesLogTheme {
    PolicySection(
        title = "Title",
        body =
            "Body body body body body body body body body body body body body body body body body body body body body body body body body body body ",
        footer = "Footer",
    )
  }
}
