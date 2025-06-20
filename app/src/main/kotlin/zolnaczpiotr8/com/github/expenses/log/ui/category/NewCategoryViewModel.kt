package zolnaczpiotr8.com.github.expenses.log.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.CategoriesRepository
import javax.inject.Inject

@HiltViewModel
class NewCategoryViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
) : ViewModel() {

    fun create(
        title: String,
    ) {
        viewModelScope.launch {
            categoriesRepository.create(title)
        }
    }
}
