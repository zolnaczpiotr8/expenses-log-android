package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

class PrivacyPolicyScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.privacy_policy)))
        .assertIsDisplayed()
  }

  @Test
  fun dataSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

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
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_your_data_stays_with_you_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun storageSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_local_only_data_storage_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun storageSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_local_only_data_storage_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun thirdPartSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_no_third_party_services_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun thirdPartSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_no_third_party_services_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun dataControlSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_data_control_and_deletion_title
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun dataControlSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(
                    R.string.privacy_policy_data_control_and_deletion_body
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun childrenSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_children_privacy_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun childrenSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_children_privacy_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun yourRightsSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_your_rights_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun yourRightsSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_your_rights_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun changesSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_changes_title)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun changesSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.privacy_policy_changes_body))
        )
        .assertIsDisplayed()
  }

  @Test
  fun contactSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

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
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_contact_information_body)
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun contactSectionFooter() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule.onNode(hasTextExactly("simkovadagmar80@gmail.com")).assertIsDisplayed()
  }

  @Test
  fun effectiveDateSectionTitle() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.policy_effective_date_title))
        )
        .assertIsDisplayed()
  }

  @Test
  fun effectiveDateSectionBody() {
    composeTestRule.setContent { PrivacyPolicyScreen() }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.privacy_policy_effective_date_body)
            )
        )
        .assertIsDisplayed()
  }
}
