package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.model.Categories
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasHeadingRole
import zolnaczpiotr8.com.github.expenses.log.feature.home.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.feature.home.isDropdownList
import zolnaczpiotr8.com.github.expenses.log.feature.home.isProgressBar
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenDateFilterIsClicked_thenDateFilterBottomSheetIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNode(
            isDropdownList() and hasText(composeTestRule.activity.getString(R.string.date_filter_this_month)),
        ).performClick()

        composeTestRule.onNode(
            isBottomSheet(),
        ).assertIsDisplayed()
    }

    @Test
    fun whenCategoriesNotEmpty_thenExpensesCategoriesIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.expenses_categories_title),
        ).assertIsDisplayed()
    }

    @Test
    fun whenCategoriesEmpty_thenExpensesCategoriesIsNotDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.expenses_categories_title),
        ).assertDoesNotExist()
    }

    @Test
    fun whenCategoriesNotEmpty_thenTotalAmountIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.total_amount_title),
        ).assertIsDisplayed()
    }

    @Test
    fun whenCategoriesEmpty_thenTotalAmountIsNotDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.total_amount_title),
        ).assertDoesNotExist()
    }

    @Test
    fun whenCategoriesNotEmpty_thenNoExpensesIsNotDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.no_expenses_title),
        ).assertDoesNotExist()
    }

    @Test
    fun whenCategoriesEmpty_thenNoExpensesIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.no_expenses_title),
        ).assertIsDisplayed()
    }

    @Test
    fun whenIsLoading_thenProgressBarIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                isLoading = true,
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNode(isProgressBar())
            .assertIsDisplayed()
    }

    @Test
    fun whenNotLoading_thenProgressBarIsNotDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                isLoading = false,
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNode(
            isProgressBar(),
        ).assertDoesNotExist()
    }

    @Test
    fun whenCategoriesAreNotEmpty_thenCategoriesTitleIsDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.expenses_categories_title))
            .assertIsDisplayed()
    }

    @Test
    fun title_hasHeadingRole() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.total_amount_title))
            .assertHasHeadingRole()
    }

    @Test
    fun mainMenuButton_onClick_showsMainMenu() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.EMPTY,
            )
        }

        composeTestRule.onNode(
            hasContentDescription(getString(coreUiR.string.menu_label)),
        ).performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    private fun getString(
        @StringRes stringRes: Int,
    ): String = composeTestRule.activity.getString(stringRes)

    @Test
    fun expenseCategoryMenuButton_onClick_showsExpenseCategoryMenu() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
            )
        }

        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(composeTestRule.activity.getString(coreUiR.string.menu_label)),
        ).onFirst()
            .performClick()

        composeTestRule.onNode(isBottomSheet())
            .assertIsDisplayed()
    }

    @Test
    fun onDeleteCategoryClick_showsDialog() {
        composeTestRule.setContent {
            HomeScreen(
                categories = Categories.ONE,
                expenses = persistentListOf(Expense.BOTTLE_OF_WATER),
            )
        }

        composeTestRule.onAllNodes(
            hasClickAction() and hasContentDescription(composeTestRule.activity.getString(coreUiR.string.menu_label)),
        ).onFirst()
            .performClick()
        composeTestRule.onNode(
            hasClickAction() and hasText(composeTestRule.activity.getString(R.string.delete_action_label)),
        ).performClick()

        composeTestRule.onNode(
            isDialog(),
        ).assertIsDisplayed()
    }
}
