package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable private data object TermsOfService

fun NavGraphBuilder.termsOfServiceDestination(
    onGoBackClick: () -> Unit = {},
) {
  composable<TermsOfService> {
    TermsOfServiceScreen(
        onGoBackClick = onGoBackClick,
    )
  }
}

fun NavController.navigateToTermsOfService() {
  navigate(
      route = TermsOfService,
  )
}
