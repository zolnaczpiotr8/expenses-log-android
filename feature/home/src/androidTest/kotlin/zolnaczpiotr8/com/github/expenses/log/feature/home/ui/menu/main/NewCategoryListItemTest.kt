package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.main

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasOnClickLabel

class NewCategoryListItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun onClickLabel() {
        composeTestRule.setContent {
            NewCategoryListItem {
            }
        }

        composeTestRule.onRoot()
            .onChild()
            .assertHasOnClickLabel(
                composeTestRule.activity.getString(R.string.add_action_label),
            )
    }
}
