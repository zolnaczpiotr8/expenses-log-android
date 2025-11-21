package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

class TermsOfServiceScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service)))
        .assertIsDisplayed()
  }

  @Test
  fun aboutSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_about_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun aboutSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service_about_body))
        )
        .assertIsDisplayed()
  }

  @Test
  fun useSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service_use_title))
        )
        .assertIsDisplayed()
  }

  @Test
  fun useSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.terms_of_service_use_body))
        )
        .assertIsDisplayed()
  }

  @Test
  fun dataSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.policy_your_data_stays_with_you_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun dataSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_your_data_stays_with_you_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun intellectualPropertyTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_intellectual_property_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun intellectualPropertyBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_intellectual_property_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun intellectualPropertyFooter() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule.onNode(hasTextExactly("Piotr Żołnacz")).assertIsDisplayed()
  }

  @Test
  fun warrantiesSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_disclaimer_of_warranties_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun warrantiesSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_disclaimer_of_warranties_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun liabilitySectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_limitation_of_liability_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun liabilitySectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_limitation_of_liability_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun terminationSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_termination_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun terminationSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_termination_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun lawSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_governing_law_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun lawSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_governing_law_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun changesSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_changes_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun changesSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_changes_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun contactSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.policy_contact_information_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun contactSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.terms_of_service_contact_information_body
                )
            )
        )
        .assertExists()
  }

  @Test
  fun contactSectionFooter() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule.onNode(hasTextExactly("simkovadagmar80@gmail.com")).assertExists()
  }

  @Test
  fun dateSectionTitle() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.policy_effective_date_title))
        )
        .assertExists()
  }

  @Test
  fun dateSectionBody() {
    composeTestRule.setContent { TermsOfServiceScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.terms_of_service_effective_date_body)
            )
        )
        .assertExists()
  }
}
