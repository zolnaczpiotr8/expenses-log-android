package zolnaczpiotr8.com.github.expenses.log.ui.home

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasOnClickLabel
import zolnaczpiotr8.com.github.expenses.log.model.Expense

private const val TITLE = "title"
private val expense =
    Expense(
        title = TITLE,
        categoryTitle = "category",
        amount = CurrencyAmount(10, Currency.getInstance("USD")),
        uuid = UUID.randomUUID(),
        created = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate(),
    )

class ExpenseListItemTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun onClick() {
    composeTestRule.setContent { ExpenseListItem(expense = expense) }

    composeTestRule.onNode(hasText(text = TITLE, substring = true)).assertHasClickAction()
  }

  @Test
  fun onClickLabel() {
    composeTestRule.setContent { ExpenseListItem(expense = expense) }

    composeTestRule
        .onNode(hasText(text = TITLE, substring = true))
        .assertHasOnClickLabel(composeTestRule.activity.getString(R.string.delete_action_label))
  }

  @Test
  fun title() {
    composeTestRule.setContent { ExpenseListItem(expense = expense) }

    composeTestRule.onNode(hasText(text = TITLE, substring = true)).assertIsDisplayed()
  }

  @Test
  fun categoryTitle() {
    composeTestRule.setContent { ExpenseListItem(expense = expense) }

    composeTestRule
        .onNode(hasText(text = expense.categoryTitle, substring = true))
        .assertIsDisplayed()
  }

  @Test
  fun deleteButton() {
    composeTestRule.setContent { ExpenseListItem(expense = expense) }

    composeTestRule
        .onNode(
            hasContentDescriptionExactly(
                composeTestRule.activity.getString(R.string.delete_action_label)
            )
        )
        .assertHasClickAction()
  }
}
