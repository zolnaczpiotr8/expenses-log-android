package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

class ShowEmptyCategoriesFilterChipTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun notSelected() {
    composeTestRule.setContent { ShowEmptyCategoriesFilterChip(isSelected = false) }

    composeTestRule.onNode(isSelected()).assertDoesNotExist()
  }

  @Test
  fun selected() {
    composeTestRule.setContent { ShowEmptyCategoriesFilterChip(isSelected = true) }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun text() {
    composeTestRule.setContent { ShowEmptyCategoriesFilterChip() }

    composeTestRule
        .onNode(hasText(composeTestRule.activity.getString(R.string.show_empty_categories_label)))
        .assertIsDisplayed()
  }
}
