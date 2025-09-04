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

class PoliciesModalBottomSheetTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun initiallyHidden() {
    composeTestRule.setContent { PoliciesModalBottomSheet() }

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun whenShowThenShown() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertIsDisplayed()
  }

  @Test
  fun whenHideThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

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
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .assertIsDisplayed()
  }

  @Test
  fun closeSheet() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

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
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule.onNode(isBottomSheet()).assertHasCollectionInfo(rowCount = 2)
  }

  @Test
  fun privacyPolicy() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.privacy_policy)))
        .assertIsDisplayed()
  }

  @Test
  fun privacyPolicyCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.privacy_policy)))
        .assertHasCollectionItemInfo(rowIndex = 0)
  }

  @Test
  fun whenPrivacyPolicyClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.privacy_policy)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }

  @Test
  fun termsOfService() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .assertIsDisplayed()
  }

  @Test
  fun termsOfServiceCollectionItemInfo() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .assertHasCollectionItemInfo(rowIndex = 1)
  }

  @Test
  fun whenTermsOfServiceClickThenHidden() {
    composeTestRule.setContent {
      val state = rememberModalBottomSheetState()
      PoliciesModalBottomSheet(state = state)

      val scope = rememberCoroutineScope()
      SideEffect { scope.launch { state.show() } }
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertDoesNotExist()
  }
}
