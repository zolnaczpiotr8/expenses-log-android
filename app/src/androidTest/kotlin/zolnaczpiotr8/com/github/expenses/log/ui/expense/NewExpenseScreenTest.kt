package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertHasEmptyEditableText
import zolnaczpiotr8.com.github.expenses.log.assertHasError
import zolnaczpiotr8.com.github.expenses.log.assertHasNoError
import zolnaczpiotr8.com.github.expenses.log.hasEditableTextExactly

private const val CATEGORY = "Test"
private const val CURRENCY_CODE = "USD"
private const val VALID_AMOUNT = "10"
private const val INVALID_AMOUNT = "-1"
private const val TITLE = "Test"

class NewExpenseScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun category() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasEditableTextExactly(CATEGORY) and
                hasText(
                    text = composeTestRule.activity.getString(R.string.category_label),
                    substring = true,
                )
        )
        .assertIsDisplayed()
  }

  @Test
  fun givenEmptyWhenSaveThenTitleHasNoError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasEditableTextExactly(CATEGORY) and
                hasText(
                    text = composeTestRule.activity.getString(R.string.category_label),
                    substring = true,
                )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.title_label),
                substring = true,
            )
        )
        .assertHasNoError()
  }

  @Test
  fun givenEmptyWhenSaveThenAmountError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasEditableTextExactly(CATEGORY) and
                hasText(
                    text = composeTestRule.activity.getString(R.string.category_label),
                    substring = true,
                )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .assertHasError()
  }

  @Test
  fun givenEmptyWhenSaveThenCategoryHasNoError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasEditableTextExactly(CATEGORY) and
                hasText(
                    text = composeTestRule.activity.getString(R.string.category_label),
                    substring = true,
                )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            ) and hasEditableTextExactly(CATEGORY)
        )
        .assertHasNoError()
  }

  @Test
  fun givenEmptyCategoryWhenSaveThenCategoryError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = "",
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .performTextInput(VALID_AMOUNT)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .assertHasError()
  }

  @Test
  fun whenSavedThenTitleCleared() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.title_label),
                substring = true,
            )
        )
        .performTextInput(TITLE)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .performTextInput(VALID_AMOUNT)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.title_label),
                substring = true,
            )
        )
        .assertHasEmptyEditableText()
  }

  @Test
  fun whenSavedThenAmountCleared() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.title_label),
                substring = true,
            )
        )
        .performTextInput(TITLE)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .performTextInput(VALID_AMOUNT)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .assertHasEmptyEditableText()
  }

  @Test
  fun whenSavedThenCategoryCleared() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.title_label),
                substring = true,
            )
        )
        .performTextInput(TITLE)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.amount_label),
                substring = true,
            )
        )
        .performTextInput(VALID_AMOUNT)

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .assertHasEmptyEditableText()
  }

  @Test
  fun givenEmptyWhenSaveThenError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = "",
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.save_error_message)))
        .assertIsDisplayed()
  }

  @Test
  fun givenEmptyAmountWhenSaveThenError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.save_error_message)))
        .assertIsDisplayed()
  }

  @Test
  fun givenInvalidAmountWhenSaveThenError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          amount = INVALID_AMOUNT,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.save_error_message)))
        .assertIsDisplayed()
  }

  @Test
  fun givenInvalidInputsWhenSaveThenNoError() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          amount = VALID_AMOUNT,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(hasTextExactly(composeTestRule.activity.getString(R.string.save_error_message)))
        .assertDoesNotExist()
  }

  @Test
  fun givenInvalidInputsWhenSaveThenSuccess() {
    composeTestRule.setContent {
      NewExpenseScreen(
          onGoBackClick = {},
          currencyCode = CURRENCY_CODE,
          category = CATEGORY,
          amount = VALID_AMOUNT,
          categoriesTitles = emptyList(),
          save = { _, _, _, _ -> },
      )
    }

    composeTestRule.mainClock.autoAdvance = false

    composeTestRule
        .onNode(
            hasText(
                text = composeTestRule.activity.getString(R.string.category_label),
                substring = true,
            )
        )
        .performImeAction()

    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule
        .onNode(
            hasTextExactly(composeTestRule.activity.getString(R.string.expense_created_message))
        )
        .assertIsDisplayed()
  }
}
