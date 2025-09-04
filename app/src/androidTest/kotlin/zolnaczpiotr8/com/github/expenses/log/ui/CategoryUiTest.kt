package zolnaczpiotr8.com.github.expenses.log.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
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
class CategoryUiTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun addOneCategory() {
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

    composeTestRule.onNodeWithText(category).assertDoesNotExist()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule.onNodeWithText(category).assertIsDisplayed()
  }

  @Test
  fun addTheSameCategoryMultipleTimes() {
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
    val categories =
        listOf(category, category, category, category, category, category, category, category)
    categories.forEach {
      composeTestRule.onNode(isEditable()).performTextInput(it)
      composeTestRule.onNode(isEditable()).performImeAction()
    }

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule.onNodeWithText(category).assertIsDisplayed()
  }

  @Test
  fun addManyCategories() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .performClick()

    val categories = listOf("Food", "Pets", "Car", "Utilities")
    categories.forEach { category ->
      composeTestRule.onNode(isEditable()).performTextInput(category)
      composeTestRule.onNode(isEditable()).performImeAction()
    }

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    categories.forEach { category -> composeTestRule.onNodeWithText(category).assertDoesNotExist() }

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    categories.forEach { category -> composeTestRule.onNodeWithText(category).assertIsDisplayed() }
  }

  @Test
  fun deleteNotEmptyCategory() {
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
        .onFirst()
        .performClick()
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.delete_action_label))
        .performClick()
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.delete_action_label))
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
        .assertDoesNotExist()
    composeTestRule.onNodeWithText(expenseTitle).assertDoesNotExist()
    composeTestRule.onNodeWithText(category).assertDoesNotExist()
  }

  @Test
  fun deleteCategory() {
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
        .onAllNodes(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .onFirst()
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .performClick()

    composeTestRule.onNodeWithText(category).assertDoesNotExist()
  }

  @Test
  fun cancelCategoryDelete() {
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
        .onAllNodes(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .onFirst()
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.cancel_action_label)))
        .performClick()

    composeTestRule.onNodeWithText(category).assertIsDisplayed()
  }
}
