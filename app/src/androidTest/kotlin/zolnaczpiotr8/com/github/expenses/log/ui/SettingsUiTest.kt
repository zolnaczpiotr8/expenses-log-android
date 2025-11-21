package zolnaczpiotr8.com.github.expenses.log.ui

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.hasEditableTextExactly
import zolnaczpiotr8.com.github.expenses.log.ui.home.toFormattedString

@HiltAndroidTest
class SettingsUiTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun defaultCurrencyInHome() {
    val currency = Currency.getInstance(ULocale.getDefault())

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .performClick()

    val category = "Food"
    composeTestRule.onNode(isEditable()).performTextInput(category)
    composeTestRule.onNode(isEditable()).performImeAction()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule
        .onAllNodesWithText(CurrencyAmount(0, currency).toFormattedString())
        .onFirst()
        .assertIsDisplayed()

    composeTestRule
        .onAllNodesWithText(CurrencyAmount(0, currency).toFormattedString())
        .onLast()
        .assertIsDisplayed()
  }

  @Test
  fun onFirstExpenseCreateCurrencyIsSaved() {
    val currency = Currency.getInstance(ULocale.getDefault())

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.agree_to_terms))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.new_expense))
        .performClick()

    val expenseTitle = "Expense"
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.title_label))
        .performTextInput(expenseTitle)

    val amount = 10
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.amount_label))
        .performTextInput(amount.toString())

    val category = "Food"
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.category_label))
        .performTextInput(category)

    composeTestRule
        .onNodeWithText(
            text = composeTestRule.activity.getString(R.string.category_label),
            substring = true,
        )
        .performImeAction()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onAllNodesWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .onLast()
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.settings))
        .performClick()

    composeTestRule.onNode(hasEditableTextExactly(currency.currencyCode)).assertIsDisplayed()
  }

  @Test
  fun defaultCurrencyInSettings() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .performClick()

    composeTestRule.onNode(hasEditableTextExactly("")).assertIsDisplayed()
  }
}
