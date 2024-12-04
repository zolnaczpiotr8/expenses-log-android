package zolnaczpiotr8.com.github.expenses.log.feature.home

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher

fun isBottomSheet() = SemanticsMatcher.expectValue(
    SemanticsProperties.PaneTitle,
    "Bottom Sheet",
)

fun isProgressBar() = SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo)

fun isDropdownList() = SemanticsMatcher.expectValue(
    SemanticsProperties.Role,
    Role.DropdownList,
)
