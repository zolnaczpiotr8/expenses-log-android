package zolnaczpiotr8.com.github.expenses.log.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
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

@HiltAndroidTest
class NavigationUiTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun firstLaunchAgreement() {
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_first_launch_agree_title)
        )
        .assertIsDisplayed()
  }

  @Test
  fun whenNotAgreedThenAgreementDialogIsVisible() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_first_launch_agree_title)
        )
        .assertIsDisplayed()
  }

  @Test
  fun termsOfServiceFromAgreementDialog() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.terms_of_service))
        .assertIsDisplayed()
  }

  @Test
  fun firstScreen() {
    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun onFirstScreenNoGoBackButton() {
    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .assertDoesNotExist()
  }

  @Test
  fun settingsScreen() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.settings))
        .assertIsDisplayed()
  }

  @Test
  fun settingsScreenGoBack() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.settings)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun newCategoryScreen() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.new_category))
        .assertIsDisplayed()
  }

  @Test
  fun newCategoryScreenGoBack() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_category)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun newExpenseScreen() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.new_expense))
        .assertIsDisplayed()
  }

  @Test
  fun newExpenseScreenGoBack() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun termsOfService() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.terms_of_service))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.terms_of_service))
        .assertIsDisplayed()
  }

  @Test
  fun termsOfServiceGoBack() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.terms_of_service))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun privacyPolicy() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.privacy_policy))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.privacy_policy))
        .assertIsDisplayed()
  }

  @Test
  fun privacyPolicyGoBack() {
    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.agree_to_terms)))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.menu_label))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.privacy_policy))
        .performClick()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.go_back_button_description)
        )
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.application_label))
        .assertIsDisplayed()
  }

  @Test
  fun navigateToNewExpenseFromCategory() {
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
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.new_expense)))
        .performClick()

    composeTestRule
        .onNodeWithText(composeTestRule.activity.getString(R.string.new_expense))
        .assertIsDisplayed()

    composeTestRule.onNode(hasEditableTextExactly(category)).assertIsDisplayed()
  }
}
