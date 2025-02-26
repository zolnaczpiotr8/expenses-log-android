package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class DeleteDialogTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun onShowState_isShown() {
        composeTestRule.setContent {
            DeleteDialog(
                isVisible = true,
            )
        }

        composeTestRule.onNode(
            isDialog(),
        ).assertIsDisplayed()
    }

    @Test
    fun onHiddenState_isHidden() {
        composeTestRule.setContent {
            DeleteDialog(
                isVisible = false,
            )
        }

        composeTestRule.onNode(
            isDialog(),
        ).assertDoesNotExist()
    }

    @Test
    fun initially_isHidden() {
        composeTestRule.setContent {
            DeleteDialog()
        }

        composeTestRule.onNode(
            isDialog(),
        ).assertDoesNotExist()
    }

    @Test
    fun givenShownState_whenRestore_thenShown() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            DeleteDialog(
                isVisible = true,
            )
        }

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNode(
            isDialog(),
        ).assertIsDisplayed()
    }

    @Test
    fun givenHiddenState_whenRestore_thenHidden() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            DeleteDialog(
                isVisible = false,
            )
        }

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNode(
            isDialog(),
        ).assertDoesNotExist()
    }
}
