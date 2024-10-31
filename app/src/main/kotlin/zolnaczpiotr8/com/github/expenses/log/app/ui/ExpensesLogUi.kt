package zolnaczpiotr8.com.github.expenses.log.app.ui

import androidx.activity.compose.ReportDrawn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.home.Home
import zolnaczpiotr8.com.github.expenses.log.feature.home.homeDestination

@Composable
internal fun ExpensesLogUi() {
    ExpensesLogTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home,
        ) {
            homeDestination()
        }
        ReportDrawn()
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        ExpensesLogUi()
    }
}
