package zolnaczpiotr8.com.github.expenses.log.ui.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasTextSelectionRange
import zolnaczpiotr8.com.github.expenses.log.hasEditableTextExactly

private const val CURRENCY_CODE = "USD"

class CurrencyComboBoxTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun text() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = CURRENCY_CODE, availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule.onNode(hasTextExactly(CURRENCY_CODE)).assertIsDisplayed()
  }

  @Test
  fun whenInputLowercaseThenUppercase() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = "", availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule.onNode(isEditable()).performTextInput(CURRENCY_CODE.lowercase())

    composeTestRule.onNode(hasEditableTextExactly(CURRENCY_CODE)).assertIsDisplayed()
  }

  @Test
  fun charactersLimit() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = "", availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule.onNode(isEditable()).performTextInput(CURRENCY_CODE)

    composeTestRule.onNode(isEditable()).performTextInput("MORE CHARACTERS")

    composeTestRule.onNode(hasEditableTextExactly(CURRENCY_CODE)).assertIsDisplayed()
  }

  @Test
  fun givenTooLongTextWhenInputThenNoTextIsInput() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = "", availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule.onNode(isEditable()).performTextInput("TOO LONG TEXT")

    composeTestRule.onNode(hasEditableTextExactly("")).assertIsDisplayed()
  }

  @Test
  fun textSelection() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = CURRENCY_CODE, availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule
        .onNode(hasTextExactly(CURRENCY_CODE))
        .assertHasTextSelectionRange(CURRENCY_CODE.length)
  }

  @Test
  fun charactersCount() {
    composeTestRule.setContent {
      CurrencyComboBox(currentCurrency = CURRENCY_CODE, availableCurrencies = listOf(CURRENCY_CODE))
    }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.character_counter_text,
                    CURRENCY_CODE.length,
                    CURRENCY_CODE.length,
                )
            )
        )
        .assertExists()
  }

  @Test
  fun onConfigurationChangeCurrencyIsPreserved() {
    val restorationTester = StateRestorationTester(composeTestRule)
    restorationTester.setContent {
      CurrencyComboBox(currentCurrency = CURRENCY_CODE, availableCurrencies = listOf(CURRENCY_CODE))
    }

    restorationTester.emulateSavedInstanceStateRestore()

    composeTestRule.onNode(hasTextExactly(CURRENCY_CODE)).assertIsDisplayed()
  }
}
