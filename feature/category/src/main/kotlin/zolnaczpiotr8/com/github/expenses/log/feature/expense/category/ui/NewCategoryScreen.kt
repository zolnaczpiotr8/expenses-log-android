package zolnaczpiotr8.com.github.expenses.log.feature.expense.category.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.buttons.extended.fab.SaveExtendedFab
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.buttons.fab.SmallFabSpacer
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.buttons.icon.buttons.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TitleTextField
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.rememberTitleTextFieldState
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.Margins
import zolnaczpiotr8.com.github.expenses.log.feature.category.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
fun NewCategoryScreen(
    viewModel: NewCategoryViewModel = hiltViewModel(),
    onGoBackClick: () -> Unit = {
    },
) {
    NewCategoryScreen(
        onGoBackClick = onGoBackClick,
        onSaveClick = viewModel::create,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewCategoryScreen(
    onGoBackClick: () -> Unit = {
    },
    onSaveClick: (String) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior()
    val titleState = rememberTitleTextFieldState()
    val scrollState = rememberScrollState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val saveErrorMessage = stringResource(coreUiR.string.save_error_message)
    val categoryCreatedMessage = stringResource(R.string.category_created_message)
    val onSave: () -> Unit = {
        titleState.validate()
        when (titleState.isError) {
            true -> scope.launch {
                snackbarHostState.showSnackbar(saveErrorMessage)
            }
            false -> {
                onSaveClick(titleState.text)
                titleState.clear()
                scope.launch {
                    snackbarHostState.showSnackbar(
                        categoryCreatedMessage,
                    )
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .imePadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },
        floatingActionButton = {
            SaveExtendedFab(
                expanded = scrollState.isScrollInProgress.not(),
                onClick = onSave,
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(coreUiR.string.new_category))
                },
                navigationIcon = {
                    GoBackIconButton(
                        onClick = onGoBackClick,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(Margins.compact)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x4),
        ) {
            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                state = titleState,
                imeAction = ImeAction.Done,
                onImeAction = onSave,
            )
            SmallFabSpacer()
        }
    }
}
