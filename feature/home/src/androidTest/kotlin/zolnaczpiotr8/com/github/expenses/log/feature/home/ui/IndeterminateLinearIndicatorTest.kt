package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class IndeterminateLinearIndicatorTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun hasGivenContentDescription() {
        val contentDescription = "test"
        composeTestRule.setContent {
            IndeterminateLinearIndicator(
                contentDescription,
            )
        }

        composeTestRule.onNodeWithContentDescription(
            contentDescription,
        ).assertIsDisplayed()
    }
}
