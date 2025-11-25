package zolnaczpiotr8.com.github.expenses.log.ui.home.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.data.ShowEmptyCategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.date.filter.DateFilterRepository
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.Expense
import zolnaczpiotr8.com.github.expenses.log.ui.common.CoroutineConfig

@HiltViewModel
internal class HomeViewModel
@Inject
constructor(
    private val categoriesRepository: CategoriesRepository,
    private val expensesRepository: ExpensesRepository,
    private val showEmptyCategoriesRepository: ShowEmptyCategoriesRepository,
    private val dateFilterRepository: DateFilterRepository,
    private val settingsRepository: SettingsRepository,
    private val expensesTransformer: ExpensesByDateTransformer,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

  val agreedToTerms: StateFlow<Boolean> =
      settingsRepository.settings
          .map { it.agreedToTerms }
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = true,
          )

  val expenses: StateFlow<Map<LocalDate, List<Expense>>> =
      expensesRepository
          .expenses()
          .distinctUntilChanged()
          .map(expensesTransformer::transform)
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = emptyMap(),
          )
  val categoriesSummary: StateFlow<CategoriesSummary?> =
      categoriesRepository
          .categories()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = null,
          )
  val dateFilter: StateFlow<DateFilter> =
      dateFilterRepository
          .dateFilter()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = DateFilter.Default,
          )
  val showEmptyCategoriesFilter: StateFlow<Boolean> =
      showEmptyCategoriesRepository
          .showEmptyCategory()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(CoroutineConfig.STOP_SHARING_TIMEOUT_MS),
              initialValue = false,
          )

  fun onAgreeToTermsClick() {
    viewModelScope.launch(coroutineDispatcher) { settingsRepository.setAgreedToTerms() }
  }

  fun onDateFilterClick(
      dateFilter: DateFilter,
  ) {
    viewModelScope.launch(coroutineDispatcher) { dateFilterRepository.update(dateFilter) }
  }

  fun onShowEmptyCategoriesFilterClick(
      show: Boolean,
  ) {
    viewModelScope.launch(coroutineDispatcher) { showEmptyCategoriesRepository.set(show) }
  }

  fun onCategoryDeleteClicked(
      uuid: UUID,
  ) {
    viewModelScope.launch(coroutineDispatcher) { categoriesRepository.delete(uuid) }
  }

  fun onExpenseDeleteClicked(
      uuid: UUID,
  ) {
    viewModelScope.launch(coroutineDispatcher) { expensesRepository.delete(uuid) }
  }
}
