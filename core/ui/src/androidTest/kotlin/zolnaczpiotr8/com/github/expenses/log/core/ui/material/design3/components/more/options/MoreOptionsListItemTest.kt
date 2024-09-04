package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.assertHasButtonRole

class MoreOptionsListItemTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun accessibility_role_isButton() {
        // GIVEN
        composeTestRule.setContent {
            OptionListItem(
                onClick = { },
                headline = "",
                leadingIcon = Icons.Default.Info,
            )
        }

        // WHEN
        // nop

        // THEN
        composeTestRule
            .onRoot()
            .onChild()
            .assertHasButtonRole()
    }
}
