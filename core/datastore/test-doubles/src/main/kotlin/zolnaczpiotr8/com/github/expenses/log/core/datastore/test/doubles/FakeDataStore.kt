package zolnaczpiotr8.com.github.expenses.log.core.datastore.test.doubles

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

class FakeDataStore<T>(initialValue: T) : DataStore<T> {
    override val data: MutableStateFlow<T> = MutableStateFlow(initialValue)
    override suspend fun updateData(transform: suspend (t: T) -> T): T = data.updateAndGet {
        transform(it)
    }
}
