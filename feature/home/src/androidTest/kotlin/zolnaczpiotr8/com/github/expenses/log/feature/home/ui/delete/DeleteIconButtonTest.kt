package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

class DeleteIconButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentDescription_isCorrect() {
        composeTestRule.setContent {
            DeleteIconButton()
        }

        composeTestRule.onRoot()
            .onChild()
            .onChild()
            .assertContentDescriptionEquals(
                composeTestRule.activity.getString(R.string.delete_action_label),
            )
    }
}
