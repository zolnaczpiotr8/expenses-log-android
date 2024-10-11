package zolnaczpiotr8.com.github.expenses.log.feature.help.and.feedback

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.navigation.NavigateUpButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpAndFeedbackScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.help_and_feedback_screen_title))
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
        HelpAndFeedbackScreen()
    }
}
