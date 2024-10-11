package zolnaczpiotr8.com.github.expenses.log.feature.expenses

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.device.filter.RequiresDisplay
import androidx.test.espresso.device.sizeclass.HeightSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class ExpensesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptions_onCompact_isBottomSheet() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_onRecreation_areRestored() {
        // GIVEN
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()
        restorationTester.emulateSavedInstanceStateRestore()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_initially_areHidden() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        // DO NOTHING

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_onButtonClick_areVisible() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_onSettingsClick_areDismissed() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()
        val settingsDescription =
            composeTestRule.activity.getString(
                R.string.settings_option_description,
            )
        composeTestRule
            .onNodeWithText(settingsDescription)
            .performClick()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_onHelpAndSupportClick_areDismissed() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()
        val helpAndSupportDescription =
            composeTestRule.activity.getString(R.string.help_and_feedback_option_description)
        composeTestRule
            .onNodeWithText(helpAndSupportDescription)
            .performClick()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertDoesNotExist()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun moreOptionsOnCompact_onShowStatisticsClick_areDismissed() {
        // GIVEN
        composeTestRule.setContent {
            ExpensesScreen()
        }

        // WHEN
        val buttonDescription =
            composeTestRule.activity.getString(coreUiR.string.more_options_button_description)
        composeTestRule
            .onNodeWithContentDescription(buttonDescription)
            .performClick()
        val showStatisticsDescription =
            composeTestRule.activity.getString(R.string.show_statistics_option_description)
        composeTestRule
            .onNodeWithText(showStatisticsDescription)
            .performClick()

        // THEN
        composeTestRule
            .onNode(isBottomSheet())
            .assertDoesNotExist()
    }
}
