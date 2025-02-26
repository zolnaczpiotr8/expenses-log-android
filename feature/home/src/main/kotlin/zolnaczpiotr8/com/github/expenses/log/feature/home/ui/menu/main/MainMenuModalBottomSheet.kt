package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import zolnaczpiotr8.com.github.expenses.log.core.ui.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.NewExpenseListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainMenuModalBottomSheet(
    state: SheetState = rememberModalBottomSheetState(),
    expensesVisible: Boolean = false,
    onNewExpenseClick: () -> Unit = {
    },
    onShowExpensesClick: () -> Unit = {
    },
    onHideExpensesClick: () -> Unit = {
    },
    onNewCategoryClick: () -> Unit = {
    },
    onSettingsClick: () -> Unit = {
    },
) {
    if (state.isVisible) {
        val scope = rememberCoroutineScope()
        ModalBottomSheet(
            modifier = Modifier.semantics {
                collectionInfo = CollectionInfo(
                    rowCount = 4,
                    columnCount = 1,
                )
            },
            onDismissRequest = {
                scope.launch {
                    state.hide()
                }
            },
            sheetState = state,
            dragHandle = {
            },
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = IncrementalPaddings.x4,
                    )
                    .padding(
                        vertical = IncrementalPaddings.x3,
                    ),
                text = stringResource(R.string.menu_label),
                style = MaterialTheme.typography.titleMedium,
            )
            NewExpenseListItem(
                modifier = Modifier
                    .semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = 0,
                            columnSpan = 1,
                            columnIndex = 0,
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
            if (expensesVisible) {
                HideExpensesListItem(
                    modifier = Modifier
                        .semantics {
                            collectionItemInfo = CollectionItemInfo(
                                rowIndex = 1,
                                columnSpan = 1,
                                columnIndex = 0,
                                rowSpan = 1,
                            )
                        },
                ) {
                    scope.launch {
                        state.hide()
                    }.invokeOnCompletion {
                        onHideExpensesClick()
                    }
                }
            } else {
                ShowExpensesListItem(
                    modifier = Modifier
                        .semantics {
                            collectionItemInfo = CollectionItemInfo(
                                rowIndex = 1,
                                columnSpan = 1,
                                columnIndex = 0,
                                rowSpan = 1,
                            )
                        },
                ) {
                    scope.launch {
                        state.hide()
                    }.invokeOnCompletion {
                        onShowExpensesClick()
                    }
                }
            }
            NewCategoryListItem(
                modifier = Modifier
                    .semantics {
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
                    onNewCategoryClick()
                }
            }
            SettingsListItem(
                modifier = Modifier
                    .semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = 3,
                            columnSpan = 1,
                            columnIndex = 0,
                            rowSpan = 1,
                        )
                    },
            ) {
                scope.launch {
                    state.hide()
                }.invokeOnCompletion {
                    onSettingsClick()
                }
            }
        }
    }
}
