package zolnaczpiotr8.com.github.expenses.log.core.data.repository

import android.icu.util.CurrencyAmount
import android.util.Log
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.Instant
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private val TAG = ExpensesRepository::class.java.name

class ExpensesRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
    private val expenseDao: ExpenseDao,
) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun delete(
        uuid: Uuid,
    ) {
        try {
            expenseDao.delete(uuid.toHexString())
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (throwable: Throwable) {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    throwable.message,
                    throwable,
                )
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun expenses(
        start: Instant,
        end: Instant,
    ): Flow<ImmutableList<Expense>> = expenseDao
        .expenses(
            start = start,
            end = end,
        )
        .combine(settingsDataSource.settings) { expenses, settings ->
            expenses
                .map {
                    Expense(
                        title = it.title,
                        categoryTitle = it.categoryTitle,
                        uuid = Uuid.parseHex(it.uuid),
                        amount = CurrencyAmount(
                            it.amount,
                            settings.currency,
                        ),
                    )
                }.toPersistentList()
        }
}
