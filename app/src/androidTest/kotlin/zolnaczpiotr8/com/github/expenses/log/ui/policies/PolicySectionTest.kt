package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.assertIsHeading

class PolicySectionTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun titleIsHeading() {
    val title = "Title"
    composeTestRule.setContent { PolicySection(title = title, body = "body") }

    composeTestRule.onNode(hasTextExactly(title)).assertIsHeading()
  }
}
