package zolnaczpiotr8.com.github.expenses.log.data

import android.icu.math.BigDecimal
import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import android.util.Log
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.model.Categories
import zolnaczpiotr8.com.github.expenses.log.model.Category
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private val TAG = CategoriesRepository::class.java.name

class CategoriesRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
    private val categoryDao: CategoryDao,
) {

    suspend fun delete(
        uuid: String,
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
    suspend fun create(
        title: String,
    ) {
        try {
            categoryDao.insert(
                CategoryEntity(
                    title = title,
                    uuid = Uuid.random().toHexString(),
                ),
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

    fun categoriesTitles(): Flow<ImmutableList<String>> = categoryDao.categoriesTitles()
        .map {
            it.toPersistentList()
        }

    fun categories(): Flow<Categories> = settingsDataSource.settings
        .map {
            if (it.currencyCode.isEmpty()) {
                Currency.getInstance(ULocale.getDefault())
            } else {
                Currency.getInstance(it.currencyCode)
            }
        }.combine(categoryDao.categories()) { currency, categories ->
            withContext(Dispatchers.Default) {
                Categories(
                    totalAmount = CurrencyAmount(
                        categories
                            .map(CategoryTotalEntity::totalAmount)
                            .reduceOrNull { first, second ->
                                first.add(second)
                            } ?: BigDecimal.ZERO,
                        currency,
                    ),
                    categories = categories.map {
                        Category(
                            uuid = it.uuid,
                            title = it.title,
                            totalAmount = CurrencyAmount(
                                it.totalAmount,
                                currency,
                            ),
                        )
                    }.toPersistentList(),
                )
            }
        }
}
