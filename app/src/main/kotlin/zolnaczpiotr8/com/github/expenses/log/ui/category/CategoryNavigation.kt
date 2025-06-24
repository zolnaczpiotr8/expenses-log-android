package zolnaczpiotr8.com.github.expenses.log.ui.category

import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
@Stable
private data object NewCategory

fun NavGraphBuilder.newCategoryDestination(
    onGoBackClick: () -> Unit = {
    },
) {
    composable<NewCategory> {
        NewCategoryScreen(
            onGoBackClick = onGoBackClick,
        )
    }
}

fun NavController.navigateToNewCategory() {
    navigate(
        route = NewCategory,
    )
}
