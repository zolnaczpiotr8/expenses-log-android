package zolnaczpiotr8.com.github.expenses.log.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsRepository
@Inject
constructor(
    private val settingsDataSource: SettingsDataSource,
) {

  val settings: Flow<Settings> = settingsDataSource.settings

  suspend fun setCurrencyCode(
      code: String,
  ): Unit = settingsDataSource.setCurrencyCode(code)
}
