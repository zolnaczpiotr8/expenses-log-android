package zolnaczpiotr8.com.github.expenses.log.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoriesRepository

@HiltViewModel
class NewCategoryViewModel
@Inject
constructor(
    private val categoriesRepository: CategoriesRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

  fun create(
      title: CharSequence,
  ) {
    viewModelScope.launch(coroutineDispatcher) { categoriesRepository.create(title.toString()) }
  }
}
