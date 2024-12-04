package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeDestination(
    onNewExpenseClick: () -> Unit = {
    },
    onSettingsClick: () -> Unit = {
    },
) {
    composable<Home> {
        HomeScreen(
            onNewExpenseClick = onNewExpenseClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
