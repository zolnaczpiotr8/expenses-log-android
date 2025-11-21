package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.extended.fab

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import zolnaczpiotr8.com.github.expenses.log.ui.ExpensesLogPreview
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
@PreviewTest
@ExpensesLogPreview
fun SaveExtendedFabPreviewExpanded() {
  ExpensesLogTheme {
    SaveExtendedFab(
        expanded = true,
    )
  }
}

@Composable
@PreviewTest
@ExpensesLogPreview
fun SaveExtendedFabPreviewCollapsed() {
  ExpensesLogTheme { SaveExtendedFab(expanded = false) }
}
