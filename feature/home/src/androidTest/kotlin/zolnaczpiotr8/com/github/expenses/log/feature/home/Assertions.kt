package zolnaczpiotr8.com.github.expenses.log.feature.home

import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert

fun SemanticsNodeInteraction.assertIsSelectableGroup(): SemanticsNodeInteraction = assert(
    SemanticsMatcher("is selectable group") {
        it.config.contains(SemanticsProperties.SelectableGroup)
    },
)

fun SemanticsNodeInteraction.assertHasCollectionItemInfo(
    rowIndex: Int,
    rowSpan: Int,
    columnIndex: Int,
    columnsSpan: Int,
): SemanticsNodeInteraction = assert(
    SemanticsMatcher("collectionItemInfo is correct") {
        val info = it.config.getOrNull(SemanticsProperties.CollectionItemInfo)
        (info?.rowIndex == rowIndex) and (info?.columnIndex == columnIndex) and (info?.rowSpan == rowSpan) and (info?.columnSpan == columnsSpan)
    },
)

fun SemanticsNodeInteraction.assertHasCollectionInfo(
    rows: Int,
    columns: Int,
): SemanticsNodeInteraction = assert(
    SemanticsMatcher("collectionInfo is correct") {
        val info = it.config.getOrNull(SemanticsProperties.CollectionInfo)
        (info?.rowCount == rows) and (info?.columnCount == columns)
    },
)

fun SemanticsNodeInteraction.assertHasOnClickLabel(
    onClickLabel: String,
): SemanticsNodeInteraction = assert(
    SemanticsMatcher("onClickLabel is set correctly") {
        it.config.getOrNull(SemanticsActions.OnClick)?.label == onClickLabel
    },
)

fun SemanticsNodeInteraction.assertHasHeadingRole(): SemanticsNodeInteraction = assert(
    SemanticsMatcher("role is heading") {
        it.config.getOrNull(SemanticsProperties.Heading) != null
    },
)

fun SemanticsNodeInteraction.assertHasCustomActionsCount(
    count: Int,
): SemanticsNodeInteraction = assert(
    SemanticsMatcher("has custom actions") {
        it.config.getOrNull(
            SemanticsActions.CustomActions,
        )?.count() == count
    },
)

fun SemanticsNodeInteraction.assertHasCustomAction(
    action: CustomAccessibilityAction,
): SemanticsNodeInteraction = assert(
    SemanticsMatcher("has custom action") {
        it.config.getOrNull(
            SemanticsActions.CustomActions,
        )?.contains(action) ?: false
    },
)
