package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.hasEditableTextExactly
import zolnaczpiotr8.com.github.expenses.log.hasError

private const val TEXT = "Test"

class TitleTextFieldTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun error() {
    composeTestRule.setContent {
      TitleTextField(state = rememberTitleTextFieldState(isError = true))
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun noError() {
    composeTestRule.setContent {
      TitleTextField(state = rememberTitleTextFieldState(isError = false))
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertDoesNotExist()
  }

  @Test
  fun charactersLimit() {
    composeTestRule.setContent { TitleTextField(state = rememberTitleTextFieldState()) }

    val veryLongText =
        "veryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongText"
    composeTestRule.onNode(isEditable()).performTextInput(veryLongText)

    composeTestRule.onNode(isEditable() and hasEditableTextExactly("")).assertIsDisplayed()
  }

  @Test
  fun clearButton() {
    composeTestRule.setContent { TitleTextField(state = rememberTitleTextFieldState()) }

    composeTestRule.onNode(isEditable()).performTextInput(TEXT)

    composeTestRule
        .onNode(
            hasClickAction() and
                hasContentDescriptionExactly(
                    composeTestRule.activity.getString(R.string.clear_action_label)
                )
        )
        .performClick()

    composeTestRule.onNode(isEditable() and hasEditableTextExactly("")).assertIsDisplayed()
  }

  @Test
  fun givenTitleWhenConfigurationChangeThenTitlePreserved() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      TitleTextField(state = rememberTitleTextFieldState(title = TEXT))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule.onNode(isEditable() and hasEditableTextExactly(TEXT)).assertIsDisplayed()
  }

  @Test
  fun givenErrorWhenConfigurationChangeThenErrorPreserved() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      TitleTextField(state = rememberTitleTextFieldState(isError = true))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun validation() {
    composeTestRule.setContent {
      val state = rememberTitleTextFieldState()
      state.validate()
      TitleTextField(state = state)
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }
}
