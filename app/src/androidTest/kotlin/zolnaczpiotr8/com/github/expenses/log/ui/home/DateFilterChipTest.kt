package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isNotSelected
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import java.time.Instant
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertIsDropdownList
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterChipTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun dropdownList() {
    composeTestRule.setContent { DateFilterChip() }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_this_month)))
        .assertIsDropdownList()
  }

  @Test
  fun whenMonthThenSelected() {
    composeTestRule.setContent { DateFilterChip(filter = DateFilter.Month) }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun whenYearThenSelected() {
    composeTestRule.setContent { DateFilterChip(filter = DateFilter.Year) }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun whenCustomThenSelected() {
    composeTestRule.setContent {
      DateFilterChip(filter = DateFilter.Custom(Instant.now(), Instant.now()))
    }

    composeTestRule.onNode(isSelected()).assertIsDisplayed()
  }

  @Test
  fun whenAnyThenNotSelected() {
    composeTestRule.setContent { DateFilterChip(filter = DateFilter.Any) }

    composeTestRule.onNode(isNotSelected()).assertIsDisplayed()
  }
}
