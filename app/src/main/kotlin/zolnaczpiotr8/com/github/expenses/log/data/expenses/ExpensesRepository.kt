package zolnaczpiotr8.com.github.expenses.log.data.expenses

import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.database.daos.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpensesRepository
@Inject
constructor(
    private val expenseDao: ExpenseDao,
    private val settingsRepository: SettingsRepository,
    private val expenseMapper: ExpenseMapper,
) {

  fun expenses(): Flow<List<Expense>> =
      expenseDao
          .expenses()
          .combine(flow = settingsRepository.currencyOrDefault, transform = expenseMapper::map)

  suspend fun create(
      title: String?,
      amount: Double,
      category: String,
      uuid: UUID = UUID.randomUUID(),
      created: Instant = Instant.now(),
  ) {
    try {
      expenseDao.insert(
          title = title,
          amount = amount,
          category = category,
          uuid = uuid,
          created = created,
      )
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }

  suspend fun delete(
      uuid: UUID,
  ) {
    try {
      expenseDao.delete(uuid)
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }
}
