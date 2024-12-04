package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.category

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.model.Category
import zolnaczpiotr8.com.github.expenses.log.feature.home.assertHasHeadingRole

class CategoryCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun title_hasHeadingRole() {
        val category = Category.FOOD
        composeTestRule.setContent {
            CategoryCard(
                category = category,
                onMenuClick = {
                },
            )
        }

        composeTestRule.onNodeWithText(category.title)
            .assertHasHeadingRole()
    }
}
