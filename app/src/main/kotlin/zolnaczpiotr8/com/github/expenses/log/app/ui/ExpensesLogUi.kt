package zolnaczpiotr8.com.github.expenses.log.app.ui

import androidx.activity.compose.ReportDrawn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.ExpensesScreen

@Composable
internal fun ExpensesLogUi() {
    ExpensesLogTheme {
        ReportDrawn()
        ExpensesScreen()
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        ExpensesLogUi()
    }
}
