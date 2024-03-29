package zolnaczpiotr8.com.github.expenses.log.feature.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.isTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun navigateUpButton_onLongClick_tooltipIsDisplayed() {
        // GIVEN
        composeTestRule.setContent {
            SettingsScreen()
        }

        // WHEN
        composeTestRule.mainClock.autoAdvance = false
        val buttonDescription =
            composeTestRule.activity.getString(R.string.navigate_up_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performTouchInput {
                longClick()
            }
        composeTestRule.mainClock.advanceTimeByFrame()

        // THEN
        composeTestRule
            .onNode(
                isTooltip(),
            ).assertIsDisplayed()
    }

    @Test
    fun navigateUpTooltip_initially_isNotDisplayed() {
        // GIVEN
        composeTestRule.setContent {
            SettingsScreen()
        }

        // WHEN
        // NOP

        // THEN
        composeTestRule
            .onNode(
                isTooltip(),
            ).assertDoesNotExist()
        val tooltipText =
            composeTestRule.activity.getString(R.string.navigate_up_button_description)
        composeTestRule
            .onNodeWithText(tooltipText)
            .assertDoesNotExist()
    }
}
