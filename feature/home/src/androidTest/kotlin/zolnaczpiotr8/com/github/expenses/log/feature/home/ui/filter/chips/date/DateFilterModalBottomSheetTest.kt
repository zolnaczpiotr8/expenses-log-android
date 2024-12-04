package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertIsSelectableGroup
import zolnaczpiotr8.com.github.expenses.log.feature.home.isBottomSheet

class DateFilterModalBottomSheetTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun isSelectableGroup() {
        composeTestRule.setContent {
            val state = rememberDateFilterSheetState()
            DateFilterModalBottomSheet(
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
            .assertIsSelectableGroup()
    }

    @Test
    fun initially_isHidden() {
        composeTestRule.setContent {
            DateFilterModalBottomSheet()
        }

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    fun onShow_isShown() {
        composeTestRule.setContent {
            val state = rememberDateFilterSheetState()
            DateFilterModalBottomSheet(
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
    fun onRestore_isShown() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            val state = rememberDateFilterSheetState()
            DateFilterModalBottomSheet(
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
    fun onAnyDateClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberDateFilterSheetState()
            DateFilterModalBottomSheet(
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
            hasText(getString(R.string.date_filter_any)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    fun onMonthClick_isDismissed() {
        composeTestRule.setContent {
            val state = rememberDateFilterSheetState()
            DateFilterModalBottomSheet(
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
            hasText(getString(R.string.date_filter_this_month)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.getString(stringRes)
}
