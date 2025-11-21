package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

private const val TEXT = "TEXT"

class DeleteDialogTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent { DeleteDialog(isVisible = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_dialog_title)))
        .assertIsDisplayed()
  }

  @Test
  fun hidden() {
    composeTestRule.setContent { DeleteDialog(isVisible = false) }

    composeTestRule.onNode(isDialog()).assertDoesNotExist()
  }

  @Test
  fun visible() {
    composeTestRule.setContent { DeleteDialog(isVisible = true) }

    composeTestRule.onNode(isDialog()).assertIsDisplayed()
  }

  @Test
  fun confirmButton() {
    composeTestRule.setContent { DeleteDialog(isVisible = true) }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)) and
                hasClickAction()
        )
        .assertIsDisplayed()
  }

  @Test
  fun dismissButton() {
    composeTestRule.setContent { DeleteDialog(isVisible = true) }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.cancel_action_label)) and
                hasClickAction()
        )
        .assertIsDisplayed()
  }

  @Test
  fun text() {
    composeTestRule.setContent { DeleteDialog(text = TEXT, isVisible = true) }

    composeTestRule.onNode(hasTextExactly(TEXT)).assertIsDisplayed()
  }
}
