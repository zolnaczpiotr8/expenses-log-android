package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasCollectionInfo
import zolnaczpiotr8.com.github.expenses.log.assertHasCollectionItemInfo
import zolnaczpiotr8.com.github.expenses.log.isBottomSheet

private const val TITLE = "Title"

class CategoryMenuModalBottomSheetTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun initiallyHidden() {
    composeTestRule.setContent { CategoryMenuModalBottomSheet() }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun whenShowThenShown() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState()
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertIsDisplayed()
  }

  @Test
  fun whenHideThenHidden() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState()
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
      SideEffect { scope.launch { state.hide() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun title() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(hasTextExactly(TITLE)).assertIsDisplayed()
  }

  @Test
  fun newExpense() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .assertIsDisplayed()
  }

  @Test
  fun newExpenseCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .assertHasCollectionItemInfo(rowIndex = 0)
  }

  @Test
  fun whenNewExpenseClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun delete() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .assertIsDisplayed()
  }

  @Test
  fun deleteCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState()
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .assertHasCollectionItemInfo(rowIndex = 1)
  }

  @Test
  fun whenDeleteClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun closeSheet() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState(title = TITLE)
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun collectionInfo() {
    composeTestRule.setContent {
      val state = rememberCategoryMenuSheetState()
      CategoryMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertHasCollectionInfo(rowCount = 2)
  }
}
