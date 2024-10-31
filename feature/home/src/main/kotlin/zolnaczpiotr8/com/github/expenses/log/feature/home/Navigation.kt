package zolnaczpiotr8.com.github.expenses.log.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeDestination() {
    composable<Home> {
        HomeScreen()
    }
}
