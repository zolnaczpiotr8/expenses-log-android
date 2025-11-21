package zolnaczpiotr8.com.github.expenses.log.ui.home

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.assertIsHeading
import zolnaczpiotr8.com.github.expenses.log.model.Category

private const val TITLE = "Title"
private val category =
    Category(
        uuid = UUID.randomUUID(),
        title = TITLE,
        totalAmount = CurrencyAmount(1.0f, Currency.getInstance(ULocale.getDefault())),
    )

class CategoryCardTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun title() {
    composeTestRule.setContent { CategoryCard(category = category, onMenuClick = {}) }

    composeTestRule.onNode(hasTextExactly(TITLE)).assertIsDisplayed()
  }

  @Test
  fun heading() {
    composeTestRule.setContent { CategoryCard(category = category, onMenuClick = {}) }

    composeTestRule.onNode(hasTextExactly(TITLE)).assertIsHeading()
  }

  @Test
  fun menuButton() {
    composeTestRule.setContent { CategoryCard(category = category, onMenuClick = {}) }

    composeTestRule
        .onNode(hasClickAction())
        .assertContentDescriptionEquals(composeTestRule.activity.getString(R.string.menu_label))
  }
}
