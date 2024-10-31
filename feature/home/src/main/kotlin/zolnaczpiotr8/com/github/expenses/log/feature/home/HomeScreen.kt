package zolnaczpiotr8.com.github.expenses.log.feature.home

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val applicationName = stringResource(R.string.application_label)
                    Text(
                        text = applicationName,
                        modifier = Modifier.clearAndSetSemantics {
                            // This hides screen title from screen reader because application name is pronounced anyway.
                            testTag = applicationName
                        },
                    )
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
        HomeScreen()
    }
}
