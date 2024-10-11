package zolnaczpiotr8.com.github.expenses.log.feature.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.navigation.NavigateUpButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = viewModel()) {
    SettingsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.settings_screen_title))
                },
                navigationIcon = {
                    NavigateUpButton {
                    }
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
        SettingsScreen()
    }
}
