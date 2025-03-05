package zolnaczpiotr8.com.github.expenses.log.core.data.repository

import android.icu.util.CurrencyAmount
import android.util.Log
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseEntity
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.core.model.Categories
import zolnaczpiotr8.com.github.expenses.log.core.model.Category
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private val TAG = CategoriesRepository::class.java.name

class CategoriesRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
    private val categoryDao: CategoryDao,
) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun delete(
        uuid: Uuid,
    ) {
        try {
            categoryDao.delete(uuid)
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
    fun categories(): Flow<Categories> = categoryDao
        .categories()
        .combine(settingsDataSource.settings) { categories, settings ->
            withContext(Dispatchers.Default) {
                Categories(
                    totalAmount = CurrencyAmount(
                        categories
                            .values
                            .flatten()
                            .map(ExpenseEntity::amount)
                            .reduceOrNull { first, second ->
                                first.add(second)
                            } ?: BigDecimal.ZERO,
                        settings.currency,
                    ),
                    categories = categories
                        .entries
                        .map {
                            Category(
                                uuid = it.key.uuid,
                                title = it.key.title,
                                totalAmount = CurrencyAmount(
                                    it.value
                                        .map(ExpenseEntity::amount)
                                        .reduceOrNull { first, second ->
                                            first.add(second)
                                        } ?: BigDecimal.ZERO,
                                    settings.currency,
                                ),
                            )
                        }.toPersistentList(),
                )
            }
        }
}
