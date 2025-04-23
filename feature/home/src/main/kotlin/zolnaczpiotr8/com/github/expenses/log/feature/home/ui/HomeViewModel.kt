package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.core.model.Categories
import zolnaczpiotr8.com.github.expenses.log.core.model.Category
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilter
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

private const val STOP_SHARING_COROUTINE_DELAY = 5_000L
private val TAG = HomeViewModel::class.java.name
private const val SHOW_EMPTY_CATEGORIES_FILTER = "SHOW_EMPTY_CATEGORIES_FILTER"
private const val DATE_FILTER = "DATE_FILTER"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expensesRepository: ExpensesRepository,
    private val categoriesRepository: CategoriesRepository,
    settingsRepository: SettingsRepository,
) : ViewModel() {

    val dateFilter: StateFlow<DateFilter> = savedStateHandle
        .getStateFlow(
            key = DATE_FILTER,
            initialValue = bundleOf(),
        ).map {
            DateFilter.fromBundle(it)
        }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = DateFilter.default,
        )

    val showEmptyCategoriesFilter: StateFlow<Boolean> = settingsRepository
        .settings
        .map {
            it.showEmptyCategories
        }
        .combine(
            savedStateHandle.getStateFlow<Boolean?>(
                key = SHOW_EMPTY_CATEGORIES_FILTER,
                initialValue = null,
            ),
        ) { global, local ->
            local ?: global
        }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = false,
        )
    private val deleteCategoryLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val categoriesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val expensesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = combine(
        categoriesLoading,
        expensesLoading,
        deleteCategoryLoading,
    ) { first, second, third ->
        first or second or third
    }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = false,
        )
    val categories: StateFlow<Categories> = categoriesRepository
        .categories(
            isNotEmpty = showEmptyCategoriesFilter.value.not(),
            start = dateFilter.value.start,
            end = dateFilter.value.end,
        )
        .catch {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    it.message,
                    it,
                )
            }
            categoriesLoading.value = false
        }.onStart {
            categoriesLoading.value = true
        }.onEach {
            categoriesLoading.value = false
        }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = Categories.EMPTY,
        )
    val expenses: StateFlow<ImmutableList<Expense>> = expensesRepository
        .expenses(
            start = dateFilter.value.start,
            end = dateFilter.value.start,
        )
        .catch {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    it.message,
                    it,
                )
            }
            expensesLoading.value = false
        }
        .onStart {
            expensesLoading.value = true
        }
        .onEach {
            expensesLoading.value = false
        }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = persistentListOf(),
        )

    fun onDateFilterClick(
        dateFilter: DateFilter,
    ) {
        savedStateHandle.set(
            key = DATE_FILTER,
            value = dateFilter.toBundle(),
        )
    }

    fun onShowEmptyCategoriesFilterClick(
        show: Boolean,
    ) {
        savedStateHandle.set(
            key = SHOW_EMPTY_CATEGORIES_FILTER,
            value = show,
        )
    }

    @OptIn(ExperimentalUuidApi::class)
    fun onCategoryDeleteClicked(
        category: Category,
    ) {
        viewModelScope.launch {
            deleteCategoryLoading.value = true
            categoriesRepository.delete(category.uuid)
            deleteCategoryLoading.value = false
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun onExpenseDeleteClicked(
        expense: Expense,
    ) {
        viewModelScope.launch {
            expensesRepository.delete(expense.uuid)
        }
    }
}
