package zolnaczpiotr8.com.github.expenses.log.feature.trash

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.device.filter.RequiresDisplay
import androidx.test.espresso.device.sizeclass.HeightSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.isTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

class TrashScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addExpenseTooltip_initially_isNotDisplayed() {
        // GIVEN
        composeTestRule.setContent {
            TrashScreen()
        }

        // WHEN
        // NOP

        // THEN
        composeTestRule
            .onNode(
                isTooltip(),
            ).assertDoesNotExist()
        val tooltipText =
            composeTestRule.activity.getString(R.string.add_expense_fab_description)
        composeTestRule
            .onNodeWithText(tooltipText)
            .assertDoesNotExist()
    }

    @Test
    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    fun addExpenseFabTooltipOnCompact_onLongClick_tooltipIsDisplayed() {
        // GIVEN
        composeTestRule.setContent {
            TrashScreen()
        }

        // WHEN
        composeTestRule.mainClock.autoAdvance = false
        val buttonDescription =
            composeTestRule.activity.getString(R.string.add_expense_fab_description)
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
        composeTestRule
            .onNodeWithText(buttonDescription)
            .assertIsDisplayed()
    }
}
