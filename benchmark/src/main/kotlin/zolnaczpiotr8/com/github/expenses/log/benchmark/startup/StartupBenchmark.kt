package zolnaczpiotr8.com.github.expenses.log.benchmark.startup

import androidx.benchmark.macro.BaselineProfileMode.Disable
import androidx.benchmark.macro.BaselineProfileMode.Require
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode.COLD
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.benchmark.BuildConfig

private const val IGNORE_CAUSE = "Macrobenchmark plugin has a bug: https://android-review.googlesource.com/c/platform/frameworks/support/+/3413763"

class StartupBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    @Ignore(IGNORE_CAUSE)
    fun startupWithPreCompilation() = startup(CompilationMode.None())

    @Test
    @Ignore(IGNORE_CAUSE)
    fun startupWithPartialCompilationAndDisabledBaselineProfile() = startup(
        CompilationMode.Partial(baselineProfileMode = Disable, warmupIterations = 1),
    )

    @Test
    @Ignore(IGNORE_CAUSE)
    fun startupPrecompiledWithBaselineProfile() = startup(CompilationMode.Partial(baselineProfileMode = Require))

    @Test
    @Ignore(IGNORE_CAUSE)
    fun startupFullyPrecompiled() = startup(CompilationMode.Full())

    private fun startup(
        compilationMode: CompilationMode,
    ) = benchmarkRule.measureRepeated(
        packageName = BuildConfig.APPLICATION_ID,
        compilationMode = compilationMode,
        metrics = listOf(StartupTimingMetric()),
        iterations = 20,
        startupMode = COLD,
        setupBlock = {
            pressHome()
        },
    ) {
        startActivityAndWait()
    }
}
