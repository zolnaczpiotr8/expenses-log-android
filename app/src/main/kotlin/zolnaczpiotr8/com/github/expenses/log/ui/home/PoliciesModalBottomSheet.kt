package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.Measurements

@Composable
fun PoliciesModalBottomSheet(
    state: SheetState = rememberModalBottomSheetState(),
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsOfServiceClick: () -> Unit = {},
) {
  val scope = rememberCoroutineScope()
  if (state.isVisible) {
    ModalBottomSheet(
        modifier =
            Modifier.semantics {
              collectionInfo =
                  CollectionInfo(
                      rowCount = 2,
                      columnCount = 1,
                  )
            },
        onDismissRequest = { scope.launch { state.hide() } },
        sheetState = state,
    ) {
      Text(
          modifier =
              Modifier.padding(
                      start = Measurements.ListItem.startPadding,
                  )
                  .padding(
                      vertical = Measurements.ListItem.verticalPadding,
                  ),
          text = stringResource(R.string.policies),
          style = MaterialTheme.typography.titleLarge,
      )

      PrivacyPolicyListItem(
          modifier =
              Modifier.semantics {
                collectionItemInfo =
                    CollectionItemInfo(
                        rowIndex = 0,
                        columnSpan = 1,
                        columnIndex = 0,
                        rowSpan = 1,
                    )
              },
      ) {
        scope.launch { state.hide() }.invokeOnCompletion { onPrivacyPolicyClick() }
      }

      TermsOfServiceListItem(
          modifier =
              Modifier.semantics {
                collectionItemInfo =
                    CollectionItemInfo(
                        rowIndex = 1,
                        columnSpan = 1,
                        columnIndex = 0,
                        rowSpan = 1,
                    )
              },
      ) {
        scope.launch { state.hide() }.invokeOnCompletion { onTermsOfServiceClick() }
      }
    }
  }
}
