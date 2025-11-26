package zolnaczpiotr8.com.github.expenses.log.benchmark.startup

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.benchmark.Config

class StartupBenchmark {
  @get:Rule val benchmarkRule = MacrobenchmarkRule()

  @Test fun startupWithoutPreCompilation() = startup(CompilationMode.None())

  @Test
  fun startupWithBaselineProfile() = startup(CompilationMode.Partial(BaselineProfileMode.Require))

  private fun startup(compilationMode: CompilationMode) {
    benchmarkRule.measureRepeated(
        packageName = Config.PACKAGE_NAME,
        iterations = Config.BENCHMARK_ITERATIONS,
        metrics = listOf(StartupTimingMetric()),
        compilationMode = compilationMode,
        startupMode = StartupMode.COLD,
        measureBlock = MacrobenchmarkScope::startActivityAndWait,
    )
  }
}
