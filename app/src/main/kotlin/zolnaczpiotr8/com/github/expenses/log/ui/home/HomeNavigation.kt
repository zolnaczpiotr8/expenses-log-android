package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeDestination(
    onNewExpenseClick: (String?) -> Unit = {
    },
    onNewCategoryClick: () -> Unit = {
    },
    onSettingsClick: () -> Unit = {
    },
) {
    composable<Home> {
        HomeScreen(
            onNewExpenseClick = onNewExpenseClick,
            onNewCategoryClick = onNewCategoryClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
