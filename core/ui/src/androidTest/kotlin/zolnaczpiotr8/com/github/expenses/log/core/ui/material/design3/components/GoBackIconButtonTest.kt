package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

class GoBackIconButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentDescription_isCorrect() {
        composeTestRule.setContent {
            GoBackIconButton {
            }
        }

        composeTestRule.onRoot()
            .onChild()
            .onChild()
            .assertContentDescriptionEquals(
                composeTestRule.activity.getString(R.string.go_back_button_description),
            )
    }
}
