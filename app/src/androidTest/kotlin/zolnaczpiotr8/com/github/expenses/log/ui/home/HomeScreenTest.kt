package zolnaczpiotr8.com.github.expenses.log.ui.home

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertIsHeading
import zolnaczpiotr8.com.github.expenses.log.isBottomSheet
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.Expense

private val totalAmount = CurrencyAmount(10, Currency.getInstance("USD"))
private const val CATEGORY_TITLE = "Test"

private val categoriesSummary =
    CategoriesSummary(
        totalAmount = totalAmount,
        categories =
            listOf(
                Category(
                    totalAmount = totalAmount,
                    uuid = UUID.randomUUID(),
                    title = CATEGORY_TITLE,
                )
            ),
    )

private const val EXPENSE_TITLE = "Expense"
private val expense =
    Expense(
        title = EXPENSE_TITLE,
        amount = totalAmount,
        categoryTitle = CATEGORY_TITLE,
        uuid = UUID.randomUUID(),
        created = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate(),
    )

private val expenses = mapOf(expense.created to listOf(expense))

class HomeScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent {
      HomeScreen(
          dateFilter = DateFilter.Any,
      )
    }

    composeTestRule
        .onNode(hasText(composeTestRule.activity.getString(R.string.application_label)))
        .assertIsDisplayed()
  }

  @Test
  fun firstLaunchAgreementDialogShown() {
    composeTestRule.setContent { HomeScreen(dateFilter = DateFilter.Any, agreedToTerms = false) }

    composeTestRule
        .onNode(
            hasText(composeTestRule.activity.getString(R.string.dialog_first_launch_agree_title))
        )
        .assertIsDisplayed()
  }

  @Test
  fun firstLaunchAgreementDialogHidden() {
    composeTestRule.setContent { HomeScreen(dateFilter = DateFilter.Any, agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasText(composeTestRule.activity.getString(R.string.dialog_first_launch_agree_title))
        )
        .assertDoesNotExist()
  }

  @Test
  fun whenEmptyThenNoExpensesHidden() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.no_expenses_title)))
        .assertDoesNotExist()
  }

  @Test
  fun whenEmptyThenNoExpensesVisible() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.no_expenses_title)))
        .assertIsDisplayed()
  }

  @Test
  fun whenMenuClickThenMenuShown() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.menu_label)))
        .assertIsDisplayed()
  }

  @Test
  fun whenNoMenuClickThenMenuHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.menu_label)))
        .assertDoesNotExist()
  }

  @Test
  fun whenPoliciesClickThenPoliciesShown() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.policies)))
        .assertIsDisplayed()
  }

  @Test
  fun initiallyExpensesHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.expenses_title)))
        .assertDoesNotExist()
  }

  @Test
  fun givenEmptyExpensesWhenShowExpensesThenHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.expenses_title)))
        .assertDoesNotExist()
  }

  @Test
  fun givenExpenseWhenShowExpensesThenExpensesShown() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true, expenses = expenses) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.expenses_title)))
        .assertIsDisplayed()
  }

  @Test
  fun whenExpensesShownThenShowExpensesButtonIsStillVisible() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true, expenses = expenses) }
    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()
    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .performClick()

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun expensesIsHeading() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true, expenses = expenses) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .performClick()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_expenses_action_label))
        )
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.expenses_title)))
        .assertIsHeading()
  }

  @Test
  fun filtersIsHeading() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.filters_title)))
        .assertIsHeading()
  }

  @Test
  fun whenNoCategoriesThenTotalAmountHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.total_amount_title)))
        .assertDoesNotExist()
  }

  @Test
  fun totalAmountIsHeading() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.total_amount_title)))
        .assertIsHeading()
  }

  @Test
  fun whenNoCategoriesThenExpensesCategoriesHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.expenses_categories_title))
        )
        .assertDoesNotExist()
  }

  @Test
  fun expensesCategoriesIsHeading() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.expenses_categories_title))
        )
        .assertIsHeading()
  }

  @Test
  fun showEmptyCategoriesFilterChipIsVisible() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.show_empty_categories_label))
        )
        .assertIsDisplayed()
  }

  @Test
  fun dateFilterChipIsVisible() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true, dateFilter = DateFilter.Any) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_any)))
        .assertIsDisplayed()
  }

  @Test
  fun whenDateFilterChipClickThenFiltersBottomSheetVisible() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true, dateFilter = DateFilter.Any) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_any)))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_title)))
        .assertIsDisplayed()
  }

  @Test
  fun initiallyDateBottomSheetIsHidden() {
    composeTestRule.setContent { HomeScreen(agreedToTerms = true) }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.date_filter_title)))
        .assertDoesNotExist()
  }

  @Test
  fun whenCategoryMenuClickThenCategoryMenuShown() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onAllNodes(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .onFirst()
        .performClick()

    composeTestRule.onNode(isBottomSheet()).assertIsDisplayed()
  }

  @Test
  fun categoryIsDisplayed() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule.onNode(hasTextExactly(CATEGORY_TITLE)).assertIsDisplayed()
  }

  @Test
  fun whenDeleteCategoryClickThenDeleteCategoryDialogVisible() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onAllNodes(
            hasContentDescriptionExactly(composeTestRule.activity.getString(R.string.menu_label))
        )
        .onFirst()
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_action_label)))
        .performClick()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_dialog_title)))
        .assertIsDisplayed()
  }

  @Test
  fun initiallyDeleteCategoryDialogIsHidden() {
    composeTestRule.setContent {
      HomeScreen(agreedToTerms = true, categoriesSummary = categoriesSummary)
    }

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.delete_dialog_title)))
        .assertDoesNotExist()
  }
}
