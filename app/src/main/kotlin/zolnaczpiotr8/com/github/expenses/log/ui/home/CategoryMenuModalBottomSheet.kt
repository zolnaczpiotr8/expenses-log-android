package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.foundation.layout.padding
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
import zolnaczpiotr8.com.github.expenses.log.ui.components.Measurements

@Composable
fun CategoryMenuModalBottomSheet(
    state: CategoryMenuSheetState = rememberCategoryMenuSheetState(),
    onNewExpenseClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
  val scope = rememberCoroutineScope()
  if (state.isHidden) {
    return
  }
  ModalBottomSheet(
      modifier =
          Modifier.semantics { collectionInfo = CollectionInfo(rowCount = 2, columnCount = 1) },
      onDismissRequest = { scope.launch { state.hide() } },
      sheetState = state.internalSheetState,
  ) {
    Text(
        modifier =
            Modifier.padding(
                    start = Measurements.ListItem.startPadding,
                )
                .padding(
                    vertical = Measurements.ListItem.verticalPadding,
                ),
        text = state.title,
        style = MaterialTheme.typography.titleLarge,
    )

    NewExpenseListItem(
        Modifier.semantics {
          collectionItemInfo =
              CollectionItemInfo(rowSpan = 1, rowIndex = 0, columnIndex = 0, columnSpan = 1)
        }
    ) {
      scope.launch { state.hide() }.invokeOnCompletion { onNewExpenseClick() }
    }
    DeleteListItem(
        Modifier.semantics {
          collectionItemInfo =
              CollectionItemInfo(rowSpan = 1, rowIndex = 1, columnIndex = 0, columnSpan = 1)
        }
    ) {
      scope.launch { state.hide() }.invokeOnCompletion { onDeleteClick() }
    }
  }
}

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
constructor(
    val internalSheetState: SheetState,
    val title: String,
) {

  val isHidden: Boolean
    get() = internalSheetState.isVisible.not()

  suspend fun show() {
    internalSheetState.show()
  }

  suspend fun hide() {
    internalSheetState.hide()
  }
}
