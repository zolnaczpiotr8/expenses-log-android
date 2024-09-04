package zolnaczpiotr8.com.github.expenses.log.feature.expenses

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.add.expense.fab.AddExpenseFab
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.add.expense.fab.AddExpenseFabTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options.MoreOptionsBottomSheet
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options.MoreOptionsButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options.MoreOptionsTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options.HelpAndFeedbackListItem
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options.SettingsListItem
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options.ShowStatisticsListItem

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = viewModel()) {
    ExpensesScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpensesScreen() {
    val scope = rememberCoroutineScope()
    val mainMenuSheetState = rememberModalBottomSheetState()
    var showMainMenu by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            AddExpenseFabTooltip {
                AddExpenseFab(
                    Modifier
                        .safeContentPadding(),
                ) {
                    // TODO
                }
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.expenses_screen_title))
                },
                actions = {
                    MoreOptionsTooltip {
                        MoreOptionsButton {
                            showMainMenu = true
                        }
                    }
                },
            )
        },
    ) {
        it.calculateTopPadding()
        val showMainMenuBottomSheet = showMainMenu
        if (showMainMenuBottomSheet) {
            MoreOptionsBottomSheet(
                onClick = {
                    scope
                        .launch {
                            mainMenuSheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (mainMenuSheetState.isVisible.not()) {
                                showMainMenu = false
                            }
                        }
                },
                onDismissRequest = {
                    showMainMenu = false
                },
                sheetState = mainMenuSheetState,
            ) {
                ShowStatisticsListItem {
                    scope
                        .launch {
                            mainMenuSheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (mainMenuSheetState.isVisible.not()) {
                                showMainMenu = false
                            }
                        }
                }
                SettingsListItem {
                    scope
                        .launch {
                            mainMenuSheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (mainMenuSheetState.isVisible.not()) {
                                showMainMenu = false
                            }
                        }
                }
                HelpAndFeedbackListItem {
                    scope
                        .launch {
                            mainMenuSheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (mainMenuSheetState.isVisible.not()) {
                                showMainMenu = false
                            }
                        }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        ExpensesScreen()
    }
}
