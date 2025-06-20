package zolnaczpiotr8.com.github.expenses.log.data

import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.model.Settings
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) {

    val settings: Flow<Settings> = settingsDataSource.settings

    suspend fun setCurrencyCode(
        code: String,
    ): Unit = settingsDataSource.setCurrencyCode(code)
}
