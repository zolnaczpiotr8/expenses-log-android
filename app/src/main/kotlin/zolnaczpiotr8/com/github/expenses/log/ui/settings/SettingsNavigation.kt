package zolnaczpiotr8.com.github.expenses.log.ui.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable private data object Settings

fun NavGraphBuilder.settingsDestination(
    onGoBackClick: () -> Unit = {},
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
