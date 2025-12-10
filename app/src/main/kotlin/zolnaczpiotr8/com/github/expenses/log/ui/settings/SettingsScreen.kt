package zolnaczpiotr8.com.github.expenses.log.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onGoBackClick: () -> Unit = {},
) {
  val availableCurrencies by viewModel.availableCurrencies.collectAsStateWithLifecycle()
  val currentCurrency by viewModel.currentCurrency.collectAsStateWithLifecycle()
  SettingsScreen(
      currentCurrency = currentCurrency,
      availableCurrencies = availableCurrencies,
      onCurrencyClick = viewModel::onCurrencyClick,
      onGoBackClick = onGoBackClick,
  )
}

@Composable
fun SettingsScreen(
    availableCurrencies: List<String>,
    currentCurrency: String,
    onGoBackClick: () -> Unit = {},
    onCurrencyClick: (String) -> Unit = {},
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  val snackbarHostState = remember { SnackbarHostState() }
  Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection).imePadding(),
      snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState,
        )
      },
      topBar = {
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(stringResource(R.string.settings)) },
            navigationIcon = {
              GoBackIconButton(
                  onClick = onGoBackClick,
              )
            },
        )
      },
  ) { paddingValues ->
    Column(
        modifier =
            Modifier.padding(paddingValues)
                .padding(Margins.compact)
                .windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Horizontal))
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
    ) {
      Row(
          modifier = Modifier.fillMaxWidth().wrapContentHeight(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(R.string.currency_label),
            style = MaterialTheme.typography.titleMedium,
        )

        val scope = rememberCoroutineScope()
        val settingsUpdatedMessage = stringResource(R.string.settings_updated_message)
        CurrencyComboBox(
            modifier = Modifier.width(200.dp),
            currentCurrency = currentCurrency,
            onCurrencyClick = {
              onCurrencyClick(it)
              scope.launch { snackbarHostState.showSnackbar(settingsUpdatedMessage) }
            },
            availableCurrencies = availableCurrencies,
        )
      }
    }
  }
}
