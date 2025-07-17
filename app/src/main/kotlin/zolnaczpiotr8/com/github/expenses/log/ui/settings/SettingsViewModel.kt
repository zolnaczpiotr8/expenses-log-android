package zolnaczpiotr8.com.github.expenses.log.ui.settings

import android.icu.util.Currency
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository

private const val STOP_SHARING_COROUTINE_DELAY = 5_000L

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

  val currentCurrency: StateFlow<String> =
      settingsRepository.settings
          .map { it.currencyCode }
          .distinctUntilChanged()
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = "",
          )
  val availableCurrencies: StateFlow<ImmutableList<String>> =
      flow<ImmutableList<String>> {
            val codes =
                Currency.getAvailableCurrencies()
                    .map(Currency::getCurrencyCode)
                    .sorted()
                    .toList()
                    .toPersistentList()
            emit(codes)
          }
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = persistentListOf(),
          )

  fun onCurrencyClick(
      code: String,
  ) {
    viewModelScope.launch { settingsRepository.setCurrencyCode(code) }
  }
}
