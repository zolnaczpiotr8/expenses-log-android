package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.expense

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.DeleteListItem
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.category.EditListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExpenseMenuModalBottomSheet(
    state: ExpenseMenuSheetState = rememberExpenseMenuSheetState(),
    onEditClick: () -> Unit = {
    },
    onDeleteClick: () -> Unit = {
    },
) {
    if (state.isHidden) {
        return
    }
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = Modifier.semantics {
            collectionInfo = CollectionInfo(
                rowCount = 2,
                columnCount = 1,
            )
        },
        onDismissRequest = {
            scope.launch {
                state.hide()
            }
        },
        sheetState = state.sheetState,
        dragHandle = {
        },
    ) {
        state.title?.let {
            Text(
                modifier = Modifier
                    .padding(
                        start = IncrementalPaddings.x4,
                    )
                    .padding(
                        vertical = IncrementalPaddings.x3,
                    ),
                text = it,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        EditListItem(
            modifier = Modifier.semantics {
                collectionItemInfo = CollectionItemInfo(
                    rowIndex = 0,
                    columnIndex = 0,
                    rowSpan = 1,
                    columnSpan = 1,
                )
            },
        ) {
            scope.launch {
                state.hide()
            }.invokeOnCompletion {
                onEditClick()
            }
        }
        DeleteListItem(
            modifier = Modifier.semantics {
                collectionItemInfo = CollectionItemInfo(
                    rowIndex = 1,
                    columnIndex = 0,
                    rowSpan = 1,
                    columnSpan = 1,
                )
            },
        ) {
            scope.launch {
                state.hide()
            }.invokeOnCompletion {
                onDeleteClick()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberExpenseMenuSheetState(
    title: String? = null,
): ExpenseMenuSheetState {
    val sheetState = rememberModalBottomSheetState()
    return remember(title) {
        ExpenseMenuSheetState(
            sheetState = sheetState,
            title = title,
        )
    }
}

internal class ExpenseMenuSheetState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    val sheetState: SheetState,
    val title: String?,
) {

    @OptIn(ExperimentalMaterial3Api::class)
    val isHidden: Boolean
        get() = sheetState.isVisible.not()

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun show() {
        sheetState.show()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun hide() {
        sheetState.hide()
    }
}
