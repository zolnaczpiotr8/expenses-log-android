package zolnaczpiotr8.com.github.expenses.log.core.data.repository

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ShowEmptyCategoriesDao
import zolnaczpiotr8.com.github.expenses.log.core.database.model.ShowEmptyCategoriesEntity
import javax.inject.Inject

private val TAG = CategoriesRepository::class.java.name

class ShowEmptyCategoriesRepository @Inject constructor(
    private val showEmptyCategoriesDao: ShowEmptyCategoriesDao,
) {

    fun showEmptyCategory(): Flow<Boolean> = showEmptyCategoriesDao.showEmptyCategories()
        .map {
            it?.value ?: false
        }

    suspend fun set(
        value: Boolean,
    ) {
        try {
            showEmptyCategoriesDao.insert(
                ShowEmptyCategoriesEntity(
                    value = value,
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
}
