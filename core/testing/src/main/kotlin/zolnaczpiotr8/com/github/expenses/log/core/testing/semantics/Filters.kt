package zolnaczpiotr8.com.github.expenses.log.core.testing.semantics

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher

fun isTooltip(): SemanticsMatcher = hasPaneTitle("tooltip")

fun isBottomSheet(): SemanticsMatcher = hasPaneTitle("Bottom Sheet")

private fun hasPaneTitle(title: String): SemanticsMatcher =
    SemanticsMatcher("${SemanticsProperties.PaneTitle.name} is $title") {
        it.config.getOrNull(SemanticsProperties.PaneTitle).contentEquals(title)
    }
