package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.expense

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionItemInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.isBottomSheet

class ExpenseMenuModalBottomSheetTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun hasCorrectCollectionInfo() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
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
                rows = 2,
                columns = 1,
            )
    }

    @Test
    fun edit_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(getString(R.string.edit_action_label))
            .assertHasCollectionItemInfo(
                rowIndex = 0,
                rowSpan = 1,
                columnsSpan = 1,
                columnIndex = 0,
            )
    }

    @Test
    fun delete_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(getString(R.string.delete_action_label))
            .assertHasCollectionItemInfo(
                rowIndex = 1,
                rowSpan = 1,
                columnsSpan = 1,
                columnIndex = 0,
            )
    }

    @Test
    fun initially_isHidden() {
        composeTestRule.setContent {
            ExpenseMenuModalBottomSheet()
        }

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    fun onShow_isShown() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
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

    @Test
    fun onShow_hasGivenTitle() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState(Expense.BOTTLE_OF_WATER.title ?: "")
            ExpenseMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(Expense.BOTTLE_OF_WATER.title ?: "")
            .assertIsDisplayed()
    }

    @Test
    fun onRestore_isShown() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
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

    @Test
    fun onEditClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNode(
            hasText(getString(R.string.edit_action_label)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    fun onDeleteClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberExpenseMenuSheetState()
            ExpenseMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNode(
            hasText(getString(R.string.delete_action_label)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.getString(stringRes)
}
