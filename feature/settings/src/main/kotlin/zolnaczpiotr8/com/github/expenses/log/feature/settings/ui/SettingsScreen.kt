package zolnaczpiotr8.com.github.expenses.log.feature.settings.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import zolnaczpiotr8.com.github.expenses.log.core.ui.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.Margins
import zolnaczpiotr8.com.github.expenses.log.feature.settings.model.SettingsUiModel

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onGoBackClick: () -> Unit = {
    },
) {
    SettingsScreen(
        uiModel = SettingsUiModel,
        onGoBackClick = onGoBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    uiModel: SettingsUiModel,
    onGoBackClick: () -> Unit = {
    },
) {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.settings))
                },
                navigationIcon = {
                    GoBackIconButton(
                        onClick = onGoBackClick,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Margins.compact),
            verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x4),
        ) {
            Spacer(
                Modifier.height(it.calculateBottomPadding())
                    .animateContentSize(),
            )
        }
    }
}
