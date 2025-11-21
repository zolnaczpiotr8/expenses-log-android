package zolnaczpiotr8.com.github.expenses.log.ui.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

private const val CURRENCY_CODE = "USD"
private val availableCurrencies = listOf(CURRENCY_CODE)

class SettingsScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent {
      SettingsScreen(availableCurrencies = availableCurrencies, currentCurrency = CURRENCY_CODE)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .assertIsDisplayed()
  }

  @Test
  fun currency() {
    composeTestRule.setContent {
      SettingsScreen(availableCurrencies = availableCurrencies, currentCurrency = CURRENCY_CODE)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.currency_label)))
        .assertIsDisplayed()
  }

  @Test
  fun goBackButton() {
    composeTestRule.setContent {
      SettingsScreen(availableCurrencies = availableCurrencies, currentCurrency = CURRENCY_CODE)
    }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.go_back_button_description)
            )
        )
        .assertIsDisplayed()
  }
}
