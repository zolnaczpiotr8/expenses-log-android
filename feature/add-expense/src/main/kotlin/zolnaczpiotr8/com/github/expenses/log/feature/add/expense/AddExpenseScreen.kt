package zolnaczpiotr8.com.github.expenses.log.feature.add.expense

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.navigation.NavigateUpButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.navigation.NavigationUpTooltip
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.add.expense.save.fab.SaveFab
import zolnaczpiotr8.com.github.expenses.log.feature.add.expense.save.fab.SaveFabTooltip

@Composable
fun AddExpenseScreen(viewModel: AddExpenseViewModel = viewModel()) {
    AddExpenseScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddExpenseScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.add_expense_screen_title))
                },
                navigationIcon = {
                    NavigationUpTooltip {
                        NavigateUpButton {
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            SaveFabTooltip {
                SaveFab(
                    Modifier
                        .safeContentPadding(),
                ) {
                }
            }
        },
    ) {
        it.calculateTopPadding()
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        AddExpenseScreen()
    }
}
