package zolnaczpiotr8.com.github.expenses.log.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.Margins
import zolnaczpiotr8.com.github.expenses.log.feature.settings.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onGoBackClick: () -> Unit = {
    },
) {
    val showEmptyCategories by viewModel.showEmptyCategories
        .collectAsStateWithLifecycle()
    SettingsScreen(
        showEmptyCategories = showEmptyCategories,
        onShowEmptyCategories = viewModel::setShowEmptyCategories,
        onGoBackClick = onGoBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    showEmptyCategories: Boolean = false,
    onShowEmptyCategories: (Boolean) -> Unit = {
    },
    onGoBackClick: () -> Unit = {
    },
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(coreUiR.string.settings))
                },
                navigationIcon = {
                    GoBackIconButton(
                        onClick = onGoBackClick,
                    )
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .padding(Margins.compact),
                verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = IncrementalPaddings.x4,
                        )
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val label = stringResource(R.string.show_empty_categories_switch_label)

                    Text(
                        modifier = Modifier
                            .clearAndSetSemantics {
                            },
                        color = MaterialTheme.colorScheme.onSurface,
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Switch(
                        modifier = Modifier.semantics {
                            contentDescription = label
                        },
                        checked = showEmptyCategories,
                        onCheckedChange = {
                            onShowEmptyCategories(it)
                        },
                    )
                }
            }
        }
    }
}
