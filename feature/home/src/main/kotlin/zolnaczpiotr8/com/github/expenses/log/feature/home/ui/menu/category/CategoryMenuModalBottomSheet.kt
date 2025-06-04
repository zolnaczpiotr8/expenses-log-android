package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.category

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
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.NewExpenseListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryMenuModalBottomSheet(
    state: CategoryMenuSheetState = rememberCategoryMenuSheetState(),
    onNewExpenseClick: () -> Unit = {
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
        sheetState = state.internalSheetState,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = IncrementalPaddings.x4,
                )
                .padding(
                    vertical = IncrementalPaddings.x3,
                ),
            text = state.title,
            style = MaterialTheme.typography.titleLarge,
        )
        NewExpenseListItem(
            modifier = Modifier.semantics {
                collectionItemInfo = CollectionItemInfo(
                    rowIndex = 0,
                    columnIndex = 0,
                    columnSpan = 1,
                    rowSpan = 1,
                )
            },
        ) {
            scope.launch {
                state.hide()
            }.invokeOnCompletion {
                onNewExpenseClick()
            }
        }
        DeleteListItem(
            modifier = Modifier.semantics {
                collectionItemInfo = CollectionItemInfo(
                    rowIndex = 2,
                    columnSpan = 1,
                    columnIndex = 0,
                    rowSpan = 1,
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
fun rememberCategoryMenuSheetState(
    title: String = "",
): CategoryMenuSheetState {
    val internalSheetState = rememberModalBottomSheetState()
    return remember(title) {
        CategoryMenuSheetState(
            internalSheetState = internalSheetState,
            title = title,
        )
    }
}

class CategoryMenuSheetState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    val internalSheetState: SheetState,
    val title: String,
) {

    @OptIn(ExperimentalMaterial3Api::class)
    val isHidden: Boolean
        get() = internalSheetState.isVisible.not()

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun show() {
        internalSheetState.show()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun hide() {
        internalSheetState.hide()
    }
}
