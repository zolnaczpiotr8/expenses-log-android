package zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.assertHasOnClickLabel
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.R

class SettingsListItemTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun accessibility_onClickLabel_isCorrect() {
        // GIVEN
        composeTestRule.setContent {
            SettingsListItem {
            }
        }

        // WHEN
        // nop

        // THEN
        val label = composeTestRule.activity.getString(R.string.navigate_on_click_label)
        composeTestRule
            .onRoot()
            .onChild()
            .assertHasOnClickLabel(label)
    }
}
