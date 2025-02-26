package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.category

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
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCollectionItemInfo
import zolnaczpiotr8.com.github.expenses.log.feature.home.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class CategoryMenuModalBottomSheetTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val title: String = "title"

    @Test
    fun hasCorrectCollectionInfo() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
                rows = 3,
                columns = 1,
            )
    }

    @Test
    fun newExpense_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            getString(coreUiR.string.new_expense),
        ).assertHasCollectionItemInfo(
            rowIndex = 0,
            columnIndex = 0,
            rowSpan = 1,
            columnsSpan = 1,
        )
    }

    @Test
    fun edit_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            getString(R.string.edit_action_label),
        ).assertHasCollectionItemInfo(
            rowIndex = 1,
            columnIndex = 0,
            rowSpan = 1,
            columnsSpan = 1,
        )
    }

    @Test
    fun delete_hasCorrectCollectionItemInfo() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            getString(R.string.delete_action_label),
        ).assertHasCollectionItemInfo(
            rowIndex = 2,
            columnIndex = 0,
            rowSpan = 1,
            columnsSpan = 1,
        )
    }

    @Test
    fun initially_isHidden() {
        composeTestRule.setContent {
            CategoryMenuModalBottomSheet()
        }

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    fun onShow_isShown() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            val state = rememberCategoryMenuSheetState(title)
            CategoryMenuModalBottomSheet(
                state = state,
            )
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    state.show()
                }
            }
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun onRestore_isShown() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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

    @Test
    fun onNewExpenseClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberCategoryMenuSheetState()
            CategoryMenuModalBottomSheet(
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
            hasText(getString(coreUiR.string.new_expense)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.getString(stringRes)
}
