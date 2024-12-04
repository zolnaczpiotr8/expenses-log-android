package zolnaczpiotr8.com.github.expenses.log.app.ui

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val appName by lazy {
        getString(R.string.application_label)
    }

    private val newExpense by lazy {
        getString(R.string.new_expense)
    }

    private val menu by lazy {
        getString(R.string.menu_label)
    }

    private val settings by lazy {
        getString(R.string.settings)
    }

    private val goBackButtonDescription by lazy {
        getString(R.string.go_back_button_description)
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.resources.getString(stringRes)

    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun homeScreen_showsAppName() {
        composeTestRule.onNodeWithTag(appName)
            .assertIsDisplayed()
    }

    @Test
    fun givenHomeScreen_onNewExpenseClick_newExpenseScreenIsShown() {
        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(menu),
        ).onLast()
            .performClick()

        composeTestRule.onNode(
            hasClickAction() and hasText(newExpense),
        ).performClick()

        composeTestRule.onNodeWithText(newExpense)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(appName)
            .assertDoesNotExist()
    }

    @Test
    fun givenHomeScreen_onSettingsClick_settingsScreenIsShown() {
        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(menu),
        ).onLast()
            .performClick()

        composeTestRule.onNode(
            hasClickAction() and hasText(settings),
        ).performClick()

        composeTestRule.onNodeWithText(settings)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(appName)
            .assertDoesNotExist()
    }

    @Test
    fun givenSettingsScreen_onGoBackClick_homeIsShown() {
        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(menu),
        ).onLast()
            .performClick()
        composeTestRule.onNode(
            hasClickAction() and hasText(settings),
        ).performClick()

        composeTestRule.onNode(
            hasClickAction() and hasContentDescription(goBackButtonDescription),
        ).performClick()

        composeTestRule.onNodeWithTag(appName)
            .assertIsDisplayed()
    }

    @Test
    fun givenNewExpenseScreen_onGoBackClick_homeIsShown() {
        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(menu),
        ).onLast()
            .performClick()
        composeTestRule.onNode(
            hasClickAction() and hasText(newExpense),
        ).performClick()

        composeTestRule.onNode(
            hasClickAction() and hasContentDescription(goBackButtonDescription),
        ).performClick()

        composeTestRule.onNodeWithTag(appName)
            .assertIsDisplayed()
    }
}
