package zolnaczpiotr8.com.github.expenses.log.benchmark.baseline.profile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.benchmark.BuildConfig

class StartupBaselineProfile {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() {
        baselineProfileRule.collect(
            BuildConfig.APPLICATION_ID,
            includeInStartupProfile = true,
            profileBlock = MacrobenchmarkScope::startActivityAndWait,
        )
    }
}
