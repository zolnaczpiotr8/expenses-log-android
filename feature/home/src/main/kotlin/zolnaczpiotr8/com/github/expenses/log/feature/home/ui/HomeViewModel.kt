package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import android.util.Log
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.core.model.Categories
import zolnaczpiotr8.com.github.expenses.log.core.model.Category
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

private const val STOP_SHARING_COROUTINE_DELAY = 5_000L
private val TAG = HomeViewModel::class.java.name

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expensesRepository: ExpensesRepository,
    private val categoriesRepository: CategoriesRepository,
) : ViewModel() {

    private val deleteExpenseLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val deleteCategoryLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val categoriesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val expensesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = combine(
        categoriesLoading,
        expensesLoading,
        deleteCategoryLoading,
        deleteExpenseLoading,
    ) { first, second, third, fourth ->
        first or second or third or fourth
    }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = false,
        )
    val categories: StateFlow<Categories> = categoriesRepository
        .categories()
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
        .expenses()
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
            deleteExpenseLoading.value = true
            expensesRepository.delete(expense.uuid)
            deleteExpenseLoading.value = false
        }
    }
}
