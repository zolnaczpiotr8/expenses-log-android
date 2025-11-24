package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.extended.fab.SaveExtendedFab
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.fab.SmallFabSpacer
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TitleTextField
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.rememberTitleTextFieldState
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

@Composable
internal fun NewExpenseScreen(
    viewModel: NewExpenseViewModel = hiltViewModel(),
    category: String?,
    onGoBackClick: () -> Unit = {},
) {
  val currencyCode by viewModel.currencyCode.collectAsStateWithLifecycle()
  val categoriesTitles by viewModel.categoriesTitles.collectAsStateWithLifecycle()
  NewExpenseScreen(
      onGoBackClick = onGoBackClick,
      currencyCode = currencyCode,
      category = category,
      categoriesTitles = categoriesTitles,
      save = viewModel::save,
  )
}

@Composable
fun NewExpenseScreen(
    onGoBackClick: () -> Unit,
    currencyCode: String,
    category: String?,
    categoriesTitles: List<String>,
    save: (CharSequence, Double, String, String) -> Unit,
    amount: String = "",
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  val titleState = rememberTitleTextFieldState()
  val amountState = rememberAmountTextFieldState(currencyCode = currencyCode, text = amount)
  val categoryState =
      rememberCategoryComboBoxState(
          category = category ?: "",
          titles = categoriesTitles,
      )
  val scrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val saveErrorMessage = stringResource(R.string.save_error_message)
  val expenseCreatedMessage = stringResource(R.string.expense_created_message)
  val onSave: () -> Unit = {
    amountState.validate()
    if (amountState.error != AmountError.None) {
      scope.launch { snackbarHostState.showSnackbar(saveErrorMessage) }
    } else {
      categoryState.validate()
      if (categoryState.isError) {
        scope.launch { snackbarHostState.showSnackbar(saveErrorMessage) }
      } else {
        save(
            titleState.textState.text,
            amountState.textState.text.toString().toDouble(),
            categoryState.textFieldState.text.toString(),
            currencyCode,
        )
        categoryState.collapse()
        categoryState.clear()
        titleState.clear()
        amountState.clear()
        scope.launch { snackbarHostState.showSnackbar(expenseCreatedMessage) }
      }
    }
  }
  Scaffold(
      modifier = Modifier.imePadding().nestedScroll(scrollBehavior.nestedScrollConnection),
      snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState,
        )
      },
      topBar = { NewExpenseTopBar(onGoBackClick = onGoBackClick, scrollBehavior = scrollBehavior) },
      floatingActionButton = {
        SaveExtendedFab(
            expanded = scrollState.isScrollInProgress.not(),
            onClick = onSave,
        )
      },
  ) { paddingValues ->
    Column(
        modifier = Modifier.verticalScroll(scrollState).padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x4),
    ) {
      val focusManager = LocalFocusManager.current

      TitleTextField(
          modifier = Modifier.padding(Margins.compact).fillMaxWidth(),
          state = titleState,
          onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
      )
      AmountTextField(
          modifier = Modifier.padding(Margins.compact).fillMaxWidth(),
          state = amountState,
          onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
      )

      CategoryComboBox(
          modifier = Modifier.padding(Margins.compact).fillMaxWidth(),
          state = categoryState,
          onImeAction = onSave,
      )

      SmallFabSpacer()
    }
  }
}
