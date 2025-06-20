package zolnaczpiotr8.com.github.expenses.log.data

import android.icu.math.BigDecimal
import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import zolnaczpiotr8.com.github.expenses.log.database.daos.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.model.Expense
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

private val TAG = ExpensesRepository::class.java.name

class ExpensesRepository @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val settingsRepository: SettingsRepository,
) {

    fun expenses(): Flow<List<Expense>> = settingsRepository.settings
        .map {
            if (it.currencyCode.isEmpty()) {
                Currency.getInstance(ULocale.getDefault())
            } else {
                Currency.getInstance(it.currencyCode)
            }
        }.combine(
            expenseDao.expenses(),
        ) { currency, expenses ->
            expenses.map {
                Expense(
                    title = it.title,
                    categoryTitle = it.categoryTitle,
                    amount = CurrencyAmount(
                        it.amount,
                        currency,
                    ),
                    uuid = it.uuid,
                    created = it.created.toLocalDateTime(TimeZone.currentSystemDefault()).date,
                )
            }
        }

    suspend fun create(
        title: String?,
        amount: BigDecimal,
        category: String,
    ) {
        try {
            expenseDao.insert(
                title = title,
                amount = amount,
                category = category,
            )
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

    suspend fun delete(
        uuid: String,
    ) {
        try {
            expenseDao.delete(uuid)
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
}
