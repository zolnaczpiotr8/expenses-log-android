package zolnaczpiotr8.com.github.expenses.log.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

class FakeDataStore<T>(initialValue: T) : DataStore<T> {

  override val data = MutableStateFlow(initialValue)

  override suspend fun updateData(
      transform: suspend (it: T) -> T,
  ) = data.updateAndGet { transform(it) }
}
