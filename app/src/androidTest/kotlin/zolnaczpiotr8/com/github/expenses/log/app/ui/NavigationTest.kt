package zolnaczpiotr8.com.github.expenses.log.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.feature.home.R

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val appName by lazy {
        composeTestRule.activity.resources.getString(R.string.application_label)
    }

    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun homeScreen_showsAppName() {
        composeTestRule.onNodeWithTag(appName)
            .assertExists()
    }
}
