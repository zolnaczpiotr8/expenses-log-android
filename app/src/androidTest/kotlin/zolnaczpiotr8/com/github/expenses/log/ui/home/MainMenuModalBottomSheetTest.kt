package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.material3.rememberModalBottomSheetState
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

class MainMenuModalBottomSheetTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun initiallyHidden() {
    composeTestRule.setContent { MainMenuModalBottomSheet() }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun whenShowThenShown() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertIsDisplayed()
  }

  @Test
  fun whenHideThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
      SideEffect { scope.launch { state.hide() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun title() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.menu_label)))
        .assertIsDisplayed()
  }

  @Test
  fun newExpense() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

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
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

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
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun showExpenses() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun showExpensesCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .assertHasCollectionItemInfo(rowIndex = 1)
  }

  @Test
  fun whenShowExpensesClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun newCategory() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .assertIsDisplayed()
  }

  @Test
  fun newCategoryCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .assertHasCollectionItemInfo(rowIndex = 2)
  }

  @Test
  fun whenNewCategoryClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun settings() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .assertIsDisplayed()
  }

  @Test
  fun settingsCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .assertHasCollectionItemInfo(rowIndex = 3)
  }

  @Test
  fun whenSettingsClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun policies() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .assertIsDisplayed()
  }

  @Test
  fun policiesCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .assertHasCollectionItemInfo(rowIndex = 4)
  }

  @Test
  fun whenPoliciesClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun closeSheet() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun collectionInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      MainMenuModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertHasCollectionInfo(rowCount = 5)
  }
}
