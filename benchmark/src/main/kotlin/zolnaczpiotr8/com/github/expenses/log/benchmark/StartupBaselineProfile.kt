package zolnaczpiotr8.com.github.expenses.log.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class StartupBaselineProfile {

  @get:Rule val baselineProfileRule = BaselineProfileRule()

  @Test
  fun generate() {
    baselineProfileRule.collect(
        packageName = "zolnaczpiotr8.com.github.expenses.log",
        includeInStartupProfile = true,
        profileBlock = MacrobenchmarkScope::startActivityAndWait,
    )
  }
}
