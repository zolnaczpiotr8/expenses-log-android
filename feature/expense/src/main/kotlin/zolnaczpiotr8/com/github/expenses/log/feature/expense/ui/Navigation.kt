package zolnaczpiotr8.com.github.expenses.log.feature.expense.ui

import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
@Stable
private data object NewExpense

fun NavGraphBuilder.newExpenseDestination(
    onGoBackClick: () -> Unit = {
    },
) {
    composable<NewExpense> {
        NewExpenseScreen(
            onGoBackClick = onGoBackClick,
        )
    }
}

fun NavController.navigateToNewExpense() {
    navigate(
        route = NewExpense,
    )
}
