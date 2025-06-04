package zolnaczpiotr8.com.github.expenses.log.core.data.repository

import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.core.model.Settings
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) {

    val settings: Flow<Settings> = settingsDataSource.settings

    suspend fun setCurrencyCode(
        code: String,
    ): Unit = settingsDataSource.setCurrencyCode(code)
}
