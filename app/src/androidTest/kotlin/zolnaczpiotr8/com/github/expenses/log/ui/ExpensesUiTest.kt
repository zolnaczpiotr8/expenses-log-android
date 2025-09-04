package zolnaczpiotr8.com.github.expenses.log.ui

import androidx.compose.ui.test.assertIsDisplayed
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

@HiltAndroidTest
class ExpensesUiTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun showExpensesWhenNoExpenses() {
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.agree_to_terms))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.expenses_title))
        .assertDoesNotExist()
  }

  @Test
  fun addExpense() {
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
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.expenses_title))
        .assertIsDisplayed()
    composeTestRule.onNodeWithText(expenseTitle).assertIsDisplayed()
    composeTestRule.onAllNodesWithText(category).onFirst().assertIsDisplayed()
    composeTestRule.onAllNodesWithText(category).onLast().assertIsDisplayed()
  }

  @Test
  fun deleteExpense() {
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
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.delete_action_label)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.delete_action_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.expenses_title))
        .assertDoesNotExist()
    composeTestRule.onNodeWithText(expenseTitle).assertDoesNotExist()
    composeTestRule.onNodeWithText(category).assertDoesNotExist()
  }
}
