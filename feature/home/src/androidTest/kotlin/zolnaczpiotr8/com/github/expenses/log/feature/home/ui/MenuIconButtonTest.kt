package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasOnClickLabel
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class MenuIconButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun onClickLabel_isCorrect() {
        composeTestRule.setContent {
            MenuIconButton {
            }
        }

        composeTestRule.onRoot()
            .onChild()
            .onChild()
            .assertHasOnClickLabel(
                composeTestRule.activity.getString(R.string.show_action_label),
            )
    }

    @Test
    fun contentDescription_isCorrect() {
        composeTestRule.setContent {
            MenuIconButton {
            }
        }

        composeTestRule.onRoot()
            .onChild()
            .onChild()
            .assertContentDescriptionEquals(
                composeTestRule.activity.getString(coreUiR.string.menu_label),
            )
    }
}
