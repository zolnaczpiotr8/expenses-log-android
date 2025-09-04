package zolnaczpiotr8.com.github.expenses.log.datastore

import androidx.datastore.core.DataStore
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsDataSource
@Inject
constructor(
    private val dataStore: DataStore<SettingsProto>,
    private val settingsMapper: SettingsMapper,
) {
  val settings: Flow<Settings> = dataStore.data.map(settingsMapper::map)

  suspend fun setCurrencyCode(
      code: String,
  ) {
    tryToUpdateData { it.toBuilder().setCurrencyCode(code).build() }
  }

  suspend fun setAgreedToTerms() {
    tryToUpdateData { it.toBuilder().setAgreedToTerms(true).build() }
  }

  private suspend fun tryToUpdateData(
      transform: suspend (t: SettingsProto) -> SettingsProto,
  ) {
    try {
      dataStore.updateData { transform(it) }
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (_: Throwable) {}
  }
}
