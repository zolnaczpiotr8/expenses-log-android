package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.isLink

class EmailTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun email() {
    composeTestRule.setContent { Text(eMail()) }

    composeTestRule.onNode(hasTextExactly("simkovadagmar80@gmail.com")).assertIsDisplayed()
  }

  @Test
  fun link() {
    composeTestRule.setContent { Text(eMail()) }

    composeTestRule.onNode(isLink()).assertIsDisplayed()
  }
}
