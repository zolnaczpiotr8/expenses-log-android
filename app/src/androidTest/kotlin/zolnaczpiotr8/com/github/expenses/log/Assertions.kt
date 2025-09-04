package zolnaczpiotr8.com.github.expenses.log

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.text.TextRange

fun SemanticsNodeInteraction.assertIsHeading(): SemanticsNodeInteraction = assert(isHeading())

fun SemanticsNodeInteraction.assertHasEmptyEditableText(): SemanticsNodeInteraction =
    assert(hasEditableTextExactly(""))

fun SemanticsNodeInteraction.assertHasError(): SemanticsNodeInteraction = assert(hasError())

fun SemanticsNodeInteraction.assertHasNoError(): SemanticsNodeInteraction = assert(hasNoError())

fun SemanticsNodeInteraction.assertIsDropdownList(): SemanticsNodeInteraction =
    assert(isDropdownList())

fun SemanticsNodeInteraction.assertHasCollectionInfo(rowCount: Int): SemanticsNodeInteraction =
    assert(hasCollectionInfo(rowCount = rowCount))

fun SemanticsNodeInteraction.assertHasCollectionItemInfo(rowIndex: Int): SemanticsNodeInteraction =
    assert(hasCollectionItemInfo(rowIndex = rowIndex))

fun SemanticsNodeInteraction.assertHasOnClickLabel(label: String): SemanticsNodeInteraction =
    assert(hasOnClickLabel(label))

fun SemanticsNodeInteraction.assertHasTextSelectionRange(index: Int): SemanticsNodeInteraction =
    assert(hasTextSelectionRange(TextRange(index)))
