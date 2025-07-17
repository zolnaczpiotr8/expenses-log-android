package zolnaczpiotr8.com.github.expenses.log.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Settings

private val TAG = SettingsDataSource::class.java.name

class SettingsDataSource
@Inject
constructor(
    private val dataStore: DataStore<SettingsProto>,
) {
  val settings: Flow<Settings> = dataStore.data.map(::toSettings)

  private fun toSettings(
      settings: SettingsProto,
  ): Settings =
      Settings(
          currencyCode = settings.currencyCode,
      )

  suspend fun setCurrencyCode(
      code: String,
  ) {
    tryToUpdateData { it.toBuilder().setCurrencyCode(code).build() }
  }

  private suspend fun tryToUpdateData(
      transform: suspend (t: SettingsProto) -> SettingsProto,
  ) {
    try {
      dataStore.updateData { transform(it) }
    } catch (cancellation: CancellationException) {
      throw cancellation
    } catch (throwable: Throwable,) {
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
