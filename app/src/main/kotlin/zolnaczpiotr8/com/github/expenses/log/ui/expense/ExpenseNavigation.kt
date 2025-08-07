package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
private data class NewExpense(
    val category: String? = null,
)

fun NavGraphBuilder.newExpenseDestination(
    onGoBackClick: () -> Unit = {},
) {
  composable<NewExpense> {
    NewExpenseScreen(
        category = it.toRoute<NewExpense>().category,
        onGoBackClick = onGoBackClick,
    )
  }
}

fun NavController.navigateToNewExpense(
    category: String? = null,
) {
  navigate(
      route = NewExpense(category),
  )
}
