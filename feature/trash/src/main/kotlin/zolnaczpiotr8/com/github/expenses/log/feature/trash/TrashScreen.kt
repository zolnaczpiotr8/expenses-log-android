package zolnaczpiotr8.com.github.expenses.log.feature.trash

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.add.expense.fab.AddExpenseFab
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun TrashScreen(viewModel: TrashViewModel = viewModel()) {
    TrashScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrashScreen() {
    Scaffold(
        floatingActionButton = {
            AddExpenseFab(
                Modifier
                    .safeContentPadding(),
            ) {
                // TODO
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.trash_screen_title))
                },
            )
        },
    ) {
        it.calculateTopPadding()
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        TrashScreen()
    }
}
