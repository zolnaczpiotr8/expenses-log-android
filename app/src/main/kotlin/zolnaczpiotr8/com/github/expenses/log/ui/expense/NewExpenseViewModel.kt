package zolnaczpiotr8.com.github.expenses.log.ui.expense

import android.icu.util.Currency
import android.icu.util.ULocale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.ui.common.CoroutineConfig

@HiltViewModel
internal class NewExpenseViewModel
@Inject
constructor(
    private val settingsRepository: SettingsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val expensesRepository: ExpensesRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

  val categoriesTitles: StateFlow<List<String>> =
      categoriesRepository
          .categoriesTitles()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = emptyList(),
          )

  val currencyCode: StateFlow<String> =
      settingsRepository.currencyCodeOrEmpty.stateIn(
          scope = viewModelScope,
          started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
          initialValue = "",
      )

  fun save(
      title: CharSequence,
      amount: Double,
      category: String,
      currencyCode: String,
  ) {
    if (currencyCode.isEmpty()) {
      val currency = Currency.getInstance(ULocale.getDefault())
      viewModelScope.launch(coroutineDispatcher) {
        settingsRepository.setCurrencyCode(currency.currencyCode)
      }
    }
    viewModelScope.launch(coroutineDispatcher) {
      categoriesRepository.create(category)
      expensesRepository.create(
          title = title.toString().takeUnless(String::isBlank),
          amount = amount,
          category = category,
      )
    }
  }
}
