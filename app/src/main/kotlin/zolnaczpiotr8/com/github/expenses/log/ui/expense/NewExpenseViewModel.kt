package zolnaczpiotr8.com.github.expenses.log.ui.expense

import android.icu.math.BigDecimal
import android.icu.util.Currency
import android.icu.util.ULocale
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import javax.inject.Inject

private val TAG = NewExpenseViewModel::class.java.name
private const val STOP_SHARING_COROUTINE_DELAY = 5_000L

@HiltViewModel
class NewExpenseViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val expensesRepository: ExpensesRepository,
) : ViewModel() {

    val categoriesTitles: StateFlow<ImmutableList<String>> = categoriesRepository
        .categoriesTitles()
        .distinctUntilChanged()
        .catch {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    it.message,
                    it,
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = persistentListOf(),
        )

    val currencyCode: StateFlow<String> = settingsRepository
        .settings
        .map {
            it.currencyCode
        }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = "",
        )

    fun save(
        title: String,
        amount: BigDecimal,
        category: String,
        currencyCode: String,
    ) {
        if (currencyCode.isEmpty()) {
            val currency = Currency.getInstance(ULocale.getDefault())
            viewModelScope.launch {
                settingsRepository.setCurrencyCode(currency.currencyCode)
            }
        }
        viewModelScope.launch {
            categoriesRepository.create(category)
            expensesRepository.create(
                title = title.takeUnless {
                    it.isBlank()
                },
                amount = amount,
                category = category,
            )
        }
    }
}
