package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.DateFilterRepository
import zolnaczpiotr8.com.github.expenses.log.data.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.data.ShowEmptyCategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.model.Categories
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

private const val STOP_SHARING_COROUTINE_DELAY = 5_000L

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val categoriesRepository: CategoriesRepository,
    private val expensesRepository: ExpensesRepository,
    private val showEmptyCategoriesRepository: ShowEmptyCategoriesRepository,
    private val dateFilterRepository: DateFilterRepository,
) : ViewModel() {

  val expenses: StateFlow<ImmutableList<ExpenseItem>> =
      expensesRepository
          .expenses()
          .distinctUntilChanged()
          .map { expenses ->
            expenses
                .flatMapIndexed { index, expense ->
                  val result = mutableListOf<ExpenseItem>()
                  ExpenseItem.Header(expense.created)
                      .takeUnless { expense.created == expenses.getOrNull(index.dec())?.created }
                      ?.let(result::add)
                  ExpenseItem.Expense(
                          title = expense.title,
                          categoryTitle = expense.categoryTitle,
                          amount = expense.amount,
                          uuid = expense.uuid,
                      )
                      .also(result::add)
                  result
                }
                .toPersistentList()
          }
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = persistentListOf(),
          )
  val categories: StateFlow<Categories?> =
      categoriesRepository
          .categories()
          .distinctUntilChanged()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = null,
          )
  val dateFilter: StateFlow<DateFilter> =
      dateFilterRepository
          .dateFilter()
          .distinctUntilChanged()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = DateFilter.Default,
          )
  val showEmptyCategoriesFilter: StateFlow<Boolean> =
      showEmptyCategoriesRepository
          .showEmptyCategory()
          .distinctUntilChanged()
          .catch {}
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
              initialValue = false,
          )

  fun onDateFilterClick(
      dateFilter: DateFilter,
  ) {
    viewModelScope.launch { dateFilterRepository.update(dateFilter) }
  }

  fun onShowEmptyCategoriesFilterClick(
      show: Boolean,
  ) {
    viewModelScope.launch { showEmptyCategoriesRepository.set(show) }
  }

  fun onCategoryDeleteClicked(
      category: Category,
  ) {
    viewModelScope.launch { categoriesRepository.delete(category.uuid) }
  }

  fun onExpenseDeleteClicked(
      uuid: String,
  ) {
    viewModelScope.launch { expensesRepository.delete(uuid) }
  }
}
