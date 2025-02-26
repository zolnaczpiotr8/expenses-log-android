package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.main

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionItemInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class MainMenuModalBottomSheetTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun hasCorrectCollectionInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNode(isBottomSheet())
            .assertHasCollectionInfo(
                rows = 4,
                columns = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun newExpense_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule
            .onNodeWithText(getString(coreUiR.string.new_expense))
            .assertHasCollectionItemInfo(
                rowIndex = 0,
                columnIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun hideExpenses_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = true,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule
            .onNodeWithText(getString(R.string.hide_expenses_action_label))
            .assertHasCollectionItemInfo(
                rowIndex = 1,
                columnIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun showExpenses_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = false,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule
            .onNodeWithText(getString(R.string.show_expenses_action_label))
            .assertHasCollectionItemInfo(
                rowIndex = 1,
                columnIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun newCategory_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule
            .onNodeWithText(getString(R.string.new_category_menu_item_headline))
            .assertHasCollectionItemInfo(
                rowIndex = 2,
                columnIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun settings_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule
            .onNodeWithText(getString(coreUiR.string.settings))
            .assertHasCollectionItemInfo(
                rowIndex = 3,
                columnIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun initially_isHidden() {
        composeTestRule.setContent {
            MainMenuModalBottomSheet()
        }

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onShow_isShown() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onRestore_isShown() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun whenExpensesNotVisible_thenShowExpensesVisible() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = false,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.show_expenses_action_label),
        ).assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun whenExpensesNotVisible_thenHideExpensesNotVisible() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = false,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.hide_expenses_action_label),
        ).assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun whenExpensesVisible_thenShowExpensesNotVisible() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = true,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.show_expenses_action_label),
        ).assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun whenExpensesVisible_thenHideExpensesVisible() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = true,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.hide_expenses_action_label),
        ).assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onHideExpensesClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                expensesVisible = true,
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.hide_expenses_action_label),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onShowExpensesClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.show_expenses_action_label),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onSettingsClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(coreUiR.string.settings),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun onNewCategoryClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberModalBottomSheetState()
            MainMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(
            getString(R.string.new_category_menu_item_headline),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.getString(stringRes)
}
