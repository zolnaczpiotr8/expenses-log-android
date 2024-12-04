package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.expense

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCustomAction
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasCustomActionsCount
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class ExpenseListItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenTitleBlank_thenTitleNotDisplayed() {
        val expense = Expense.NO_TITLE
        composeTestRule.setContent {
            ExpenseListItem(
                expense = expense,
            )
        }

        composeTestRule.onNodeWithText(expense.title ?: "")
            .assertDoesNotExist()
    }

    @Test
    fun whenTitleNotBlank_thenTitleDisplayed() {
        val expense = Expense.BOTTLE_OF_WATER
        composeTestRule.setContent {
            ExpenseListItem(
                expense = expense,
            )
        }

        composeTestRule.onNodeWithText(expense.title ?: "")
            .assertIsDisplayed()
    }

    @Test
    fun customActionsCount() {
        composeTestRule.setContent {
            ExpenseListItem(
                expense = Expense.BOTTLE_OF_WATER,
            )
        }

        composeTestRule
            .onRoot()
            .onChild()
            .assertHasCustomActionsCount(3)
    }

    @Test
    fun hasEditCustomAction() {
        val editAction = {
            true
        }
        composeTestRule.setContent {
            ExpenseListItem(
                expense = Expense.BOTTLE_OF_WATER,
                onEditClick = editAction,
            )
        }

        composeTestRule.onRoot()
            .onChild()
            .assertHasCustomAction(
                CustomAccessibilityAction(
                    label = composeTestRule.activity.getString(R.string.edit_action_label),
                    action = editAction,
                ),
            )
    }

    @Test
    fun hasDeleteCustomAction() {
        val deleteAction = {
            true
        }
        composeTestRule.setContent {
            ExpenseListItem(
                expense = Expense.BOTTLE_OF_WATER,
                onDeleteClick = deleteAction,
            )
        }

        composeTestRule.onRoot()
            .onChild()
            .assertHasCustomAction(
                CustomAccessibilityAction(
                    label = composeTestRule.activity.getString(R.string.delete_action_label),
                    action = deleteAction,
                ),
            )
    }

    @Test
    fun hasDetailsCustomAction() {
        val detailsAction = {
            true
        }
        composeTestRule.setContent {
            ExpenseListItem(
                expense = Expense.BOTTLE_OF_WATER,
                onDetailsClick = detailsAction,
            )
        }

        composeTestRule.onRoot()
            .onChild()
            .assertHasCustomAction(
                CustomAccessibilityAction(
                    label = composeTestRule.activity.getString(R.string.details_action_label),
                    action = detailsAction,
                ),
            )
    }

    @Test
    fun menuButton_isHidden() {
        composeTestRule.setContent {
            ExpenseListItem(
                expense = Expense.BOTTLE_OF_WATER,
            )
        }

        composeTestRule.onNode(
            hasClickAction() or hasContentDescription(composeTestRule.activity.getString(coreUiR.string.menu_label)),
        ).assertDoesNotExist()
    }
}
