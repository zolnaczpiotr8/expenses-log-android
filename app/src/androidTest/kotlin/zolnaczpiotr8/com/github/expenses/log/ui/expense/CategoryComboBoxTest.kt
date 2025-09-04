package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.hasEditableTextExactly
import zolnaczpiotr8.com.github.expenses.log.hasError

private const val TEXT = "Test"

class CategoryComboBoxTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun error() {
    composeTestRule.setContent {
      CategoryComboBox(state = rememberCategoryComboBoxState(isError = true))
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun charactersLimit() {
    composeTestRule.setContent { CategoryComboBox(state = rememberCategoryComboBoxState()) }

    val veryLongText =
        "veryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongText"
    composeTestRule.onNode(isEditable()).performTextInput(veryLongText)

    composeTestRule.onNode(isEditable() and hasEditableTextExactly("")).assertIsDisplayed()
  }

  @Test
  fun givenTitleWhenConfigurationChangeThenTitlePreserved() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      CategoryComboBox(state = rememberCategoryComboBoxState(category = TEXT))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule.onNode(isEditable() and hasEditableTextExactly(TEXT)).assertIsDisplayed()
  }

  @Test
  fun givenErrorWhenConfigurationChangeThenErrorPreserved() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      CategoryComboBox(state = rememberCategoryComboBoxState(isError = true))
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
      val state = rememberCategoryComboBoxState()
      state.validate()
      CategoryComboBox(state = state)
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }
}
