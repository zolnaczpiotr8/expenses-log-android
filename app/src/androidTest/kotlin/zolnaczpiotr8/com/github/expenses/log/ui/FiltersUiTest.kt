package zolnaczpiotr8.com.github.expenses.log.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
class FiltersUiTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun whenShowEmptyCategoriesNotSelectedThenNoEmptyCategories() {
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
        .assertIsNotSelected()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.no_expenses_title))
        .assertIsDisplayed()
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.total_amount_title))
        .assertDoesNotExist()
    composeTestRule.onNodeWithText(category).assertDoesNotExist()
  }

  @Test
  fun whenShowEmptyCategoriesSelectedThenNoEmptyCategories() {
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
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .assertIsSelected()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.no_expenses_title))
        .assertDoesNotExist()
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.total_amount_title))
        .assertIsDisplayed()
    composeTestRule.onNodeWithText(category).assertIsDisplayed()
  }

  @Test
  fun selectShowEmptyCategories() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .assertIsSelected()
  }

  @Test
  fun unelectShowEmptyCategories() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        .assertIsNotSelected()
  }

  @Test
  fun monthDateFilterIsSelectedByDefault() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.date_filter_this_month))
        .assertIsSelected()
  }
}
