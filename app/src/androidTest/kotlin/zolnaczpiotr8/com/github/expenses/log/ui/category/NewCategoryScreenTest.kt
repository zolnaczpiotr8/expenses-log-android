package zolnaczpiotr8.com.github.expenses.log.ui.category

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasEmptyEditableText

class NewCategoryScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent { NewCategoryScreen(onSaveClick = {}, onGoBackClick = {}) }

    composeTestRule
        .onNode(hasText(composeTestRule.activity.getString(R.string.new_category)))
        .assertIsDisplayed()
  }

  @Test
  fun givenEmptyTitleWhenSaveThenError() {
    composeTestRule.setContent { NewCategoryScreen(onSaveClick = {}, onGoBackClick = {}) }
    composeTestRule.mainClock.autoAdvance = false

    composeTestRule.onNode(isEditable()).performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.save_error_message)))
        .assertIsDisplayed()
  }

  @Test
  fun givenValidTitleWhenSaveThenSuccess() {
    composeTestRule.setContent { NewCategoryScreen(onSaveClick = {}, onGoBackClick = {}) }

    composeTestRule.onNode(isEditable()).performTextInput("Food")

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule.onNode(isEditable()).performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.category_created_message))
        )
        .assertIsDisplayed()
  }

  @Test
  fun givenValidTitleWhenSaveThenTitleCleared() {
    composeTestRule.setContent { NewCategoryScreen(onSaveClick = {}, onGoBackClick = {}) }

    composeTestRule.onNode(isEditable()).performTextInput("Food")

    composeTestRule.onNode(isEditable()).performImeAction()

    composeTestRule.onNode(isEditable()).assertHasEmptyEditableText()
  }
}
