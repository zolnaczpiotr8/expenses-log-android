package zolnaczpiotr8.com.github.expenses.log.ui

import androidx.activity.compose.ReportDrawn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import zolnaczpiotr8.com.github.expenses.log.ui.category.navigateToNewCategory
import zolnaczpiotr8.com.github.expenses.log.ui.category.newCategoryDestination
import zolnaczpiotr8.com.github.expenses.log.ui.expense.navigateToNewExpense
import zolnaczpiotr8.com.github.expenses.log.ui.expense.newExpenseDestination
import zolnaczpiotr8.com.github.expenses.log.ui.home.Home
import zolnaczpiotr8.com.github.expenses.log.ui.home.homeDestination
import zolnaczpiotr8.com.github.expenses.log.ui.policies.navigateToPrivacyPolicy
import zolnaczpiotr8.com.github.expenses.log.ui.policies.navigateToTermsOfService
import zolnaczpiotr8.com.github.expenses.log.ui.policies.privacyPolicyDestination
import zolnaczpiotr8.com.github.expenses.log.ui.policies.termsOfServiceDestination
import zolnaczpiotr8.com.github.expenses.log.ui.settings.navigateToSettings
import zolnaczpiotr8.com.github.expenses.log.ui.settings.settingsDestination
import zolnaczpiotr8.com.github.expenses.log.ui.theme.ExpensesLogTheme

@Composable
fun ExpensesLogUi() {
  ExpensesLogTheme {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
      homeDestination(
          onNewExpenseClick = navController::navigateToNewExpense,
          onSettingsClick = navController::navigateToSettings,
          onNewCategoryClick = navController::navigateToNewCategory,
          onPrivacyPolicyClick = navController::navigateToPrivacyPolicy,
          onTermsOfServiceClick = navController::navigateToTermsOfService)
      settingsDestination(
          onGoBackClick = navController::popBackStack,
      )
      newExpenseDestination(
          onGoBackClick = navController::popBackStack,
      )
      newCategoryDestination(
          onGoBackClick = navController::popBackStack,
      )
      privacyPolicyDestination(onGoBackClick = navController::popBackStack)
      termsOfServiceDestination(onGoBackClick = navController::popBackStack)
    }
  }
  ReportDrawn()
}
