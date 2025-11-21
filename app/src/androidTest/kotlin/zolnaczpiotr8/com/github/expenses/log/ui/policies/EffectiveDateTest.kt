package zolnaczpiotr8.com.github.expenses.log.ui.policies

import android.icu.util.ULocale
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test

class EffectiveDateTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun us() {
    composeTestRule.setContent { Text(text = rememberEffectiveDate(ULocale.US)) }

    composeTestRule.onRoot().onChild().assertTextEquals("August 1, 2025")
  }

  @Test
  fun italy() {
    composeTestRule.setContent { Text(text = rememberEffectiveDate(ULocale.ITALY)) }

    composeTestRule.onRoot().onChild().assertTextEquals("1 Agosto 2025")
  }
}
