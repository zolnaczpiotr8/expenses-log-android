package zolnaczpiotr8.com.github.expenses.log.benchmark

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class NewCategoryBaselineProfile {
  @get:Rule val baselineProfileRule = BaselineProfileRule()

  @Test
  fun generate() {
    baselineProfileRule.collect(
        packageName = Config.PACKAGE_NAME,
        profileBlock = {
          startActivityAndWait()
          with(device) {
            agreeToTermsIfNeeded()
            goToNewCategory()
            addNewCategory()
            clickGoBack()
          }
        },
    )
  }
}
