package zolnaczpiotr8.com.github.expenses.log.app.ui

import androidx.activity.compose.ReportDrawn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.expense.ui.navigateToNewExpense
import zolnaczpiotr8.com.github.expenses.log.feature.expense.ui.newExpenseDestination
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.Home
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.homeDestination
import zolnaczpiotr8.com.github.expenses.log.feature.settings.ui.navigateToSettings
import zolnaczpiotr8.com.github.expenses.log.feature.settings.ui.settingsDestination

@Composable
internal fun ExpensesLogUi() {
    ExpensesLogTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home,
        ) {
            homeDestination(
                onNewExpenseClick = navController::navigateToNewExpense,
                onSettingsClick = navController::navigateToSettings,
            )
            newExpenseDestination(
                onGoBackClick = navController::popBackStack,
            )
            settingsDestination(
                onGoBackClick = navController::popBackStack,
            )
        }
        ReportDrawn()
    }
}
