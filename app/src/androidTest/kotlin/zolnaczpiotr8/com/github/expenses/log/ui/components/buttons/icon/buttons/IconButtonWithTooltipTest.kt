package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.performTouchInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.isTooltip

private const val LABEL = "label"

class IconButtonWithTooltipTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun contentDescription() {
    composeTestRule.setContent {
      IconButtonWithTooltip(
          imageVector = Icons.Default.Save,
          label = LABEL,
      )
    }

    composeTestRule.onNode(hasClickAction()).assertContentDescriptionEquals(LABEL)
  }

  @Test
  fun whenNoInteractionThenNoTooltip() {
    composeTestRule.setContent {
      IconButtonWithTooltip(
          imageVector = Icons.Default.Save,
          label = LABEL,
      )
    }

    composeTestRule.onNode(isTooltip()).assertDoesNotExist()
  }

  @Test
  fun whenLongClickThenTooltip() {
    composeTestRule.setContent {
      IconButtonWithTooltip(
          imageVector = Icons.Default.Save,
          label = LABEL,
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule.onNode(hasClickAction()).performTouchInput { longClick() }

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule.onNode(isTooltip()).onChild().onChild().assertTextEquals(LABEL)
  }
}
