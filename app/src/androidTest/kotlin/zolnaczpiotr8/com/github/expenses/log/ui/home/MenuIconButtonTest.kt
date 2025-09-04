package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasOnClickLabel

class MenuIconButtonTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun onClickLabel() {
    composeTestRule.setContent { MenuIconButton {} }

    composeTestRule
        .onNode(hasClickAction())
        .assertHasOnClickLabel(composeTestRule.activity.getString(R.string.show_action_label))
  }
}
