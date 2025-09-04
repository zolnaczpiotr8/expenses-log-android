package zolnaczpiotr8.com.github.expenses.log

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.text.TextRange

fun hasNoError(): SemanticsMatcher =
    SemanticsMatcher("Error") { it.config.getOrNull(SemanticsProperties.Error) == null }

fun hasError(): SemanticsMatcher =
    SemanticsMatcher("Error") { it.config.getOrNull(SemanticsProperties.Error) != null }

fun hasError(error: String): SemanticsMatcher =
    SemanticsMatcher("Error '$error'") { it.config.getOrNull(SemanticsProperties.Error) == error }

fun hasEditableTextExactly(text: String): SemanticsMatcher =
    SemanticsMatcher("Editable text '$text'") {
      it.config.getOrNull(SemanticsProperties.EditableText)?.text == text
    }

fun isHeading(): SemanticsMatcher =
    SemanticsMatcher("isHeading") { it.config.getOrNull(SemanticsProperties.Heading) == Unit }

fun isTooltip(): SemanticsMatcher =
    SemanticsMatcher("Tooltip") { it.config.getOrNull(SemanticsProperties.PaneTitle) == "tooltip" }

fun isRadioButton(): SemanticsMatcher =
    SemanticsMatcher("Radio button") {
      it.config.getOrNull(SemanticsProperties.Role) == Role.RadioButton
    }

fun hasOnClickLabel(label: String): SemanticsMatcher =
    SemanticsMatcher("On click label") {
      it.config.getOrNull(SemanticsActions.OnClick)?.label == label
    }

fun isSelectableGroup(): SemanticsMatcher =
    SemanticsMatcher("Selectable group") {
      it.config.getOrNull(SemanticsProperties.SelectableGroup) == Unit
    }

fun isBottomSheet(): SemanticsMatcher =
    SemanticsMatcher("Bottom sheet") {
      it.config.getOrNull(SemanticsProperties.PaneTitle) == "Bottom Sheet"
    }

fun isDropdownList(): SemanticsMatcher =
    SemanticsMatcher("Dropdown list") {
      it.config.getOrNull(SemanticsProperties.Role) == Role.DropdownList
    }

fun hasCollectionInfo(
    rowCount: Int,
): SemanticsMatcher =
    SemanticsMatcher("Collection info rows=$rowCount") {
      val info = it.config.getOrNull(SemanticsProperties.CollectionInfo)
      (info?.rowCount == rowCount) and (info?.columnCount == 1)
    }

fun hasCollectionItemInfo(rowIndex: Int): SemanticsMatcher =
    SemanticsMatcher("Collection item info row index = $rowIndex") {
      val info = it.config.getOrNull(SemanticsProperties.CollectionItemInfo)
      (info?.rowIndex == rowIndex) and
          (info?.columnIndex == 0) and
          (info?.rowSpan == 1) and
          (info?.columnSpan == 1)
    }

fun isLink(): SemanticsMatcher =
    SemanticsMatcher("Is link") { it.config.getOrNull(SemanticsProperties.LinkTestMarker) != null }

fun hasTextSelectionRange(textRange: TextRange): SemanticsMatcher =
    SemanticsMatcher("Text range $textRange") {
      it.config.getOrNull(SemanticsProperties.TextSelectionRange) == textRange
    }
