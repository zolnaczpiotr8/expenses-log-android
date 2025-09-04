package zolnaczpiotr8.com.github.expenses.log.ui.settings

import android.icu.util.Currency
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.ui.common.CoroutineConfig

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    private val settingsRepository: SettingsRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

  val currentCurrency: StateFlow<String> =
      settingsRepository.currencyCodeOrEmpty.stateIn(
          scope = viewModelScope,
          started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
          initialValue = "",
      )
  val availableCurrencies: StateFlow<List<String>> =
      flow {
            val codes = Currency.getAvailableCurrencies().map(Currency::getCurrencyCode).sorted()
            emit(codes)
          }
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = emptyList(),
          )

  fun onCurrencyClick(
      code: String,
  ) {
    viewModelScope.launch(coroutineDispatcher) { settingsRepository.setCurrencyCode(code) }
  }
}
