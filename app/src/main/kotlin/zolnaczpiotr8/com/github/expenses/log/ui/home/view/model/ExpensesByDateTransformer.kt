package zolnaczpiotr8.com.github.expenses.log.ui.home.view.model

import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpensesByDateTransformer
@Inject
constructor(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {

  suspend fun transform(expenses: List<Expense>): Map<LocalDate, List<Expense>> =
      withContext(dispatcher) { expenses.groupBy(Expense::created).toSortedMap(reverseOrder()) }
}
