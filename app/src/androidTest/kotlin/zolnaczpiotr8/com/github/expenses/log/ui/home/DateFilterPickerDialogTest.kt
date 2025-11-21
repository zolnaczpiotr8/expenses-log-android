package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

class DateFilterPickerDialogTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun dialog() {
    composeTestRule.setContent { DateFilterPickerDialog() }

    composeTestRule.onNode(isDialog()).assertIsDisplayed()
  }

  @Test
  fun cancelButton() {
    composeTestRule.setContent { DateFilterPickerDialog() }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.cancel_action_label)))
        .assertHasClickAction()
  }

  @Test
  fun okButton() {
    composeTestRule.setContent { DateFilterPickerDialog() }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.confirm_action_label)))
        .assertHasClickAction()
  }
}
