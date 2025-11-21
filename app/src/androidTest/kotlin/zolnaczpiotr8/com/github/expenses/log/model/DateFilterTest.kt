package zolnaczpiotr8.com.github.expenses.log.model

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

class DateFilterTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun month() {
    composeTestRule.setContent { Text(text = toFormattedString(DateFilter.Month)) }

    composeTestRule
        .onRoot()
        .onChild()
        .assertTextEquals(composeTestRule.activity.getString(R.string.date_filter_this_month))
  }

  @Test
  fun year() {
    composeTestRule.setContent { Text(text = toFormattedString(DateFilter.Year)) }

    composeTestRule
        .onRoot()
        .onChild()
        .assertTextEquals(composeTestRule.activity.getString(R.string.date_filter_this_year))
  }

  @Test
  fun any() {
    composeTestRule.setContent { Text(text = toFormattedString(DateFilter.Any)) }

    composeTestRule
        .onRoot()
        .onChild()
        .assertTextEquals(composeTestRule.activity.getString(R.string.date_filter_any))
  }
}
