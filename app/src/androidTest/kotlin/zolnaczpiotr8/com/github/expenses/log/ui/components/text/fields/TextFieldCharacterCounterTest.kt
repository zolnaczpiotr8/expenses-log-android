package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R

private const val COUNT = 10
private const val LIMIT = 30

class TextFieldCharacterCounterTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun contentDescription() {
    composeTestRule.setContent { TextFieldCharacterCounter(count = COUNT, limit = LIMIT) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(
                    R.string.character_count_and_limit_label,
                    COUNT,
                    LIMIT,
                )
            )
        )
        .assertIsDisplayed()
  }

  @Test
  fun text() {
    composeTestRule.setContent { TextFieldCharacterCounter(count = COUNT, limit = LIMIT) }

    composeTestRule
        .onNode(
            hasTextExactly(
                composeTestRule.activity.getString(R.string.character_counter_text, COUNT, LIMIT)
            )
        )
        .assertIsDisplayed()
  }
}
