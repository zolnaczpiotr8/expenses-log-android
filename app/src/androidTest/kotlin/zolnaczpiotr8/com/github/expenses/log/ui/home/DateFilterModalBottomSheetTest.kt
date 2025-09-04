package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.isSelectableGroup

class DateFilterModalBottomSheetTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun initiallyHidden() {
    composeTestRule.setContent { DateFilterModalBottomSheet() }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun selected() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun onShowIsShown() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule.onNode(isBottomSheet()).assertIsDisplayed()
  }

  @Test
  fun selectableGroup() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule.onNode(isSelectableGroup()).assertIsDisplayed()
  }

  @Test
  fun title() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_title)))
        .assertIsDisplayed()
  }

  @Test
  fun monthIsDisplayed() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.date_filter_this_month)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun anyIsDisplayed() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.date_filter_any)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun yearIsDisplayed() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.date_filter_this_year)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun customNotSelectedIsDisplayed() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.date_filter_custom)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun onHideIsHidden() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)

      SideEffect { scope.launch { state.hide() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun closeSheet() {
    composeTestRule.setContent {
      val scope = rememberCoroutineScope()
      val state = rememberDateFilterSheetState()
      SideEffect { scope.launch { state.show() } }
      DateFilterModalBottomSheet(state = state)
    }

    composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }
}
