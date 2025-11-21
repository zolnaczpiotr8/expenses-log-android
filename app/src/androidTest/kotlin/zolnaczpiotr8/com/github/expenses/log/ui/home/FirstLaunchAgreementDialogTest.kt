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

class FirstLaunchAgreementDialogTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun dialog() {
    composeTestRule.setContent { FirstLaunchAgreementDialog(onAgreeClick = {}) }

    composeTestRule.onNode(isDialog()).assertIsDisplayed()
  }

  @Test
  fun title() {
    composeTestRule.setContent { FirstLaunchAgreementDialog(onAgreeClick = {}) }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.dialog_first_launch_agree_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun text() {
    composeTestRule.setContent { FirstLaunchAgreementDialog(onAgreeClick = {}) }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.dialog_first_launch_terms_message)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun confirmButton() {
    composeTestRule.setContent { FirstLaunchAgreementDialog(onAgreeClick = {}) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .assertHasClickAction()
  }

  @Test
  fun dismissButton() {
    composeTestRule.setContent { FirstLaunchAgreementDialog(onAgreeClick = {}) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .assertHasClickAction()
  }
}
