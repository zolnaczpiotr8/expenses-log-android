package zolnaczpiotr8.com.github.expenses.log.benchmark.home

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.benchmark.Config
import zolnaczpiotr8.com.github.expenses.log.benchmark.addNewCategory
import zolnaczpiotr8.com.github.expenses.log.benchmark.addNewExpense
import zolnaczpiotr8.com.github.expenses.log.benchmark.agreeToTermsIfNeeded
import zolnaczpiotr8.com.github.expenses.log.benchmark.clickGoBack
import zolnaczpiotr8.com.github.expenses.log.benchmark.clickMainMenu
import zolnaczpiotr8.com.github.expenses.log.benchmark.clickShowEmptyCategoriesIfNotChecked
import zolnaczpiotr8.com.github.expenses.log.benchmark.clickShowExpenses
import zolnaczpiotr8.com.github.expenses.log.benchmark.goToNewCategory
import zolnaczpiotr8.com.github.expenses.log.benchmark.goToNewExpense
import zolnaczpiotr8.com.github.expenses.log.benchmark.scrollCategoriesDown
import zolnaczpiotr8.com.github.expenses.log.benchmark.scrollCategoriesUp
import zolnaczpiotr8.com.github.expenses.log.benchmark.scrollExpensesDown
import zolnaczpiotr8.com.github.expenses.log.benchmark.scrollExpensesUp
import zolnaczpiotr8.com.github.expenses.log.benchmark.waitForExpensesCategories
import zolnaczpiotr8.com.github.expenses.log.benchmark.waitForHome

class HomeBaselineProfile {

  @get:Rule val baselineProfileRule = BaselineProfileRule()

  @Test
  fun generateScrollingExpenses() {
    baselineProfileRule.collect(
        packageName = Config.PACKAGE_NAME,
        profileBlock = {
          startActivityAndWait()

          with(device) {
            agreeToTermsIfNeeded()
            goToNewExpense()
            (1..Config.ITEMS_FOR_SCROLLING).forEach { _ -> addNewExpense() }
            clickGoBack()
            waitForHome()
            clickMainMenu()
            clickShowExpenses()
            scrollExpensesDown()
            scrollExpensesUp()
          }
        },
    )
  }

  @Test
  fun generateScrollingCategories() {
    baselineProfileRule.collect(
        packageName = Config.PACKAGE_NAME,
        profileBlock = {
          startActivityAndWait()

          with(device) {
            agreeToTermsIfNeeded()
            goToNewCategory()
            (1..Config.ITEMS_FOR_SCROLLING).forEach { _ -> addNewCategory() }
            clickGoBack()
            waitForHome()
            clickShowEmptyCategoriesIfNotChecked()
            waitForExpensesCategories()
            scrollCategoriesDown()
            scrollCategoriesUp()
          }
        },
    )
  }
}
