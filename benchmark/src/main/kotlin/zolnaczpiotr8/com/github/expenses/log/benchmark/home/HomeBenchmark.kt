package zolnaczpiotr8.com.github.expenses.log.benchmark.home

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.benchmark.Config
import zolnaczpiotr8.com.github.expenses.log.benchmark.addNewCategory
import zolnaczpiotr8.com.github.expenses.log.benchmark.addNewExpense
import zolnaczpiotr8.com.github.expenses.log.benchmark.agreeToTermsIfNeeded
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

class HomeBenchmark {

  @get:Rule val benchmarkRule = MacrobenchmarkRule()

  @Test fun scrollingExpensesWithoutPreCompilation() = scrollingExpenses(CompilationMode.None())

  @Test
  fun scrollingExpensesWithBaselineProfile() =
      scrollingExpenses(CompilationMode.Partial(BaselineProfileMode.Require))

  private fun scrollingExpenses(compilationMode: CompilationMode) {
    benchmarkRule.measureRepeated(
        packageName = Config.PACKAGE_NAME,
        iterations = Config.BENCHMARK_ITERATIONS,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = compilationMode,
        startupMode = StartupMode.COLD,
        setupBlock = {
          startActivityAndWait()

          with(device) {
            agreeToTermsIfNeeded()
            goToNewExpense()
            (1..Config.ITEMS_FOR_SCROLLING).forEach { _ -> addNewExpense() }
          }
        },
        measureBlock = {
          startActivityAndWait()

          with(device) {
            clickMainMenu()
            clickShowExpenses()
            scrollExpensesDown()
            scrollExpensesUp()
          }
        },
    )
  }

  @Test fun scrollingCategoriesWithoutPreCompilation() = scrollingCategories(CompilationMode.None())

  @Test
  fun scrollingCategoriesWithBaselineProfile() =
      scrollingCategories(CompilationMode.Partial(BaselineProfileMode.Require))

  private fun scrollingCategories(compilationMode: CompilationMode) {
    benchmarkRule.measureRepeated(
        packageName = Config.PACKAGE_NAME,
        iterations = Config.BENCHMARK_ITERATIONS,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = compilationMode,
        startupMode = StartupMode.COLD,
        setupBlock = {
          startActivityAndWait()

          with(device) {
            agreeToTermsIfNeeded()
            goToNewCategory()
            (1..Config.ITEMS_FOR_SCROLLING).forEach { _ -> addNewCategory() }
          }
        },
        measureBlock = {
          startActivityAndWait()

          with(device) {
            clickShowEmptyCategoriesIfNotChecked()
            waitForExpensesCategories()
            scrollCategoriesDown()
            scrollCategoriesUp()
          }
        },
    )
  }
}
