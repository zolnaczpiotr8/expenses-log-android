package zolnaczpiotr8.com.github.expenses.log.feature.settings.ui

import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
@Stable
private data object Settings

fun NavGraphBuilder.settingsDestination(
    onGoBackClick: () -> Unit = {
    },
) {
    composable<Settings> {
        SettingsScreen(
            onGoBackClick = onGoBackClick,
        )
    }
}

fun NavController.navigateToSettings() {
    navigate(
        route = Settings,
    )
}
