package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.isNotSelected
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.hasOnClickLabel
import zolnaczpiotr8.com.github.expenses.log.isRadioButton

private const val TEXT = "Text"

class RadioButtonRowTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun radioButtonRole() {
    composeTestRule.setContent { RadioButtonRow(isSelected = false, text = TEXT) {} }

    composeTestRule.onNode(isRadioButton()).assertIsDisplayed()
  }

  @Test
  fun contentDescription() {
    composeTestRule.setContent { RadioButtonRow(isSelected = false, text = TEXT) {} }

    composeTestRule.onNode(hasContentDescriptionExactly(TEXT))
  }

  @Test
  fun selected() {
    composeTestRule.setContent { RadioButtonRow(isSelected = true, text = TEXT) {} }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun notSelected() {
    composeTestRule.setContent { RadioButtonRow(isSelected = false, text = TEXT) {} }

    composeTestRule.onNode(isNotSelected()).assertIsDisplayed()
  }

  @Test
  fun onClickLabel() {
    composeTestRule.setContent { RadioButtonRow(isSelected = false, text = TEXT) {} }

    composeTestRule
        .onNode(hasOnClickLabel(composeTestRule.activity.getString(R.string.choose_action_label)))
        .assertIsDisplayed()
  }
}
