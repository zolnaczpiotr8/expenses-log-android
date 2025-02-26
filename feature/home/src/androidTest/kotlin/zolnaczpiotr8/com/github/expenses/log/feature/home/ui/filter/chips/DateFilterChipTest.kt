package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.datetime.Clock
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.isDropdownList
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilter
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilterChip

class DateFilterChipTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun hasDropDownListRole() {
        composeTestRule.setContent {
            DateFilterChip()
        }

        composeTestRule.onNode(
            isDropdownList(),
        ).assertIsDisplayed()
    }

    @Test
    fun whenAnyDateState_thenHasDateLabel() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.AnyDate,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_title),
        ).assertIsDisplayed()
    }

    @Test
    fun whenAnyDateState_thenIsNotSelected() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.AnyDate,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_title),
        ).assertIsNotSelected()
    }

    @Test
    fun whenYearState_thenHasYearLabel() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Year,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_year),
        ).assertIsDisplayed()
    }

    @Test
    fun whenYearState_thenIsSelected() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Year,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_year),
        ).assertIsSelected()
    }

    @Test
    fun whenMonthState_thenHasMonthLabel() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Month,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_month),
        ).assertIsDisplayed()
    }

    @Test
    fun whenMonthState_thenIsSelected() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Month,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_month),
        ).assertIsSelected()
    }

    @Test
    fun whenQuarterState_thenHasQuarterLabel() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Quarter,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_quarter),
        ).assertIsDisplayed()
    }

    @Test
    fun whenQuarterState_thenIsSelected() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Quarter,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_this_quarter),
        ).assertIsSelected()
    }

    @Test
    fun whenCustomState_thenHasCustomLabel() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Custom(
                    start = Clock.System.now(),
                    end = Clock.System.now(),
                ),
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_custom),
        ).assertIsDisplayed()
    }

    @Test
    fun whenCustomState_thenIsSelected() {
        composeTestRule.setContent {
            DateFilterChip(
                filter = DateFilter.Custom(
                    start = Clock.System.now(),
                    end = Clock.System.now(),
                ),
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.date_filter_custom),
        ).assertIsSelected()
    }
}
