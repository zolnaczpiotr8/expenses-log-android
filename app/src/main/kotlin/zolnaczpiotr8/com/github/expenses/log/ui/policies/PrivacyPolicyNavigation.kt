package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable private data object PrivacyPolicy

fun NavGraphBuilder.privacyPolicyDestination(
    onGoBackClick: () -> Unit = {},
) {
  composable<PrivacyPolicy> {
    PrivacyPolicyScreen(
        onGoBackClick = onGoBackClick,
    )
  }
}

fun NavController.navigateToPrivacyPolicy() {
  navigate(
      route = PrivacyPolicy,
  )
}
