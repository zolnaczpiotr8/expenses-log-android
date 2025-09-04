package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasText
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

class AmountTextFieldTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun label() {
    composeTestRule.setContent { AmountTextField() }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun emptyError() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Empty))
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun formatError() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Format))
    }

    composeTestRule
        .onNode(hasError(composeTestRule.activity.getString(R.string.invalid_format_error)))
        .assertIsDisplayed()
  }

  @Test
  fun noError() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.None))
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.invalid_format_error)) or
                hasError(
                    composeTestRule.activity.getString(R.string.required_text_field_error_label)
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun inputText() {
    composeTestRule.setContent { AmountTextField(state = rememberAmountTextFieldState()) }

    composeTestRule.onNode(isEditable()).performTextInput(TEXT)

    composeTestRule.onNode(isEditable() and hasEditableTextExactly(TEXT)).assertIsDisplayed()
  }

  @Test
  fun charactersLimit() {
    composeTestRule.setContent { AmountTextField(state = rememberAmountTextFieldState()) }

    val veryLongText =
        "veryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongTextveryLongText"
    composeTestRule.onNode(isEditable()).performTextInput(veryLongText)

    composeTestRule.onNode(isEditable() and hasEditableTextExactly("")).assertIsDisplayed()
  }

  @Test
  fun clearButton() {
    composeTestRule.setContent { AmountTextField(state = rememberAmountTextFieldState()) }

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
  fun whenEmptyThenClearButtonHidden() {
    composeTestRule.setContent { AmountTextField(state = rememberAmountTextFieldState()) }

    composeTestRule
        .onNode(
            hasClickAction() and
                hasContentDescriptionExactly(
                    composeTestRule.activity.getString(R.string.clear_action_label)
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun whenEmptyErrorThenClearButtonHidden() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Empty))
    }

    composeTestRule
        .onNode(
            hasClickAction() and
                hasContentDescriptionExactly(
                    composeTestRule.activity.getString(R.string.clear_action_label)
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun whenFormatErrorThenClearButtonHidden() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Format))
    }

    composeTestRule
        .onNode(
            hasClickAction() and
                hasContentDescriptionExactly(
                    composeTestRule.activity.getString(R.string.clear_action_label)
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun givenEmptyWhenValidationThenError() {
    composeTestRule.setContent {
      val state = rememberAmountTextFieldState()
      state.validate()
      AmountTextField(state = state)
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun givenInvalidFormatWhenValidationThenError() {
    composeTestRule.setContent {
      val state = rememberAmountTextFieldState(text = TEXT)
      state.validate()
      AmountTextField(state = state)
    }

    composeTestRule
        .onNode(hasError(composeTestRule.activity.getString(R.string.invalid_format_error)))
        .assertIsDisplayed()
  }

  @Test
  fun givenNegativeAmountWhenValidationThenError() {
    composeTestRule.setContent {
      val state = rememberAmountTextFieldState(text = "-1")
      state.validate()
      AmountTextField(state = state)
    }

    composeTestRule
        .onNode(hasError(composeTestRule.activity.getString(R.string.invalid_format_error)))
        .assertIsDisplayed()
  }

  @Test
  fun givenValidInputWhenValidationThenNoError() {
    composeTestRule.setContent {
      val state = rememberAmountTextFieldState(text = "1")
      state.validate()
      AmountTextField(state = state)
    }

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.invalid_format_error)) or
                hasError(
                    composeTestRule.activity.getString(R.string.required_text_field_error_label)
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun whenNoErrorThenCharacterCounterVisible() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(text = TEXT))
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(
                    R.string.character_count_and_limit_label,
                    TEXT.length,
                    AMOUNT_CHARACTERS_LIMIT,
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun whenEmptyErrorThenCharacterCounterHidden() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Empty))
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(
                    R.string.character_count_and_limit_label,
                    0,
                    AMOUNT_CHARACTERS_LIMIT,
                )
            )
        )
        .assertDoesNotExist()
  }

  @Test
  fun whenEmptyErrorThenErrorTextVisible() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Empty))
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.required_text_field_error_label),
                substring = true,
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun whenInvalidFormatErrorThenErrorTextVisible() {
    composeTestRule.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Format))
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.invalid_format_error),
                substring = true,
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun whenNoErrorThenErrorTextHidden() {
    composeTestRule.setContent { AmountTextField(state = rememberAmountTextFieldState()) }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.required_text_field_error_label),
                substring = true,
            ) or
                hasText(
                    text = composeTestRule.activity.getString(R.string.invalid_format_error),
                    substring = true,
                )
        )
        .assertDoesNotExist()
  }

  @Test
  fun givenTextWhenRestoredThenText() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      AmountTextField(state = rememberAmountTextFieldState(text = TEXT))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule.onNode(isEditable() and hasEditableTextExactly(TEXT)).assertIsDisplayed()
  }

  @Test
  fun givenEmptyErrorWhenRestoredThenEmptyError() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Empty))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule
        .onNode(
            hasError(composeTestRule.activity.getString(R.string.required_text_field_error_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun givenInvalidFormatErrorWhenRestoredThenInvalidFormatError() {
    val restorationTester = StateRestorationTester(composeTestRule)

    restorationTester.setContent {
      AmountTextField(state = rememberAmountTextFieldState(error = AmountError.Format))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule
        .onNode(hasError(composeTestRule.activity.getString(R.string.invalid_format_error)))
        .assertIsDisplayed()
  }
}
