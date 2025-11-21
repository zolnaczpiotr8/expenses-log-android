package zolnaczpiotr8.com.github.expenses.log.data

import android.icu.util.Currency
import android.icu.util.ULocale
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsRepository
@Inject
constructor(
    private val settingsDataSource: SettingsDataSource,
) {

  val settings: Flow<Settings> = settingsDataSource.settings

  val currencyCodeOrEmpty: Flow<String> = settingsDataSource.settings.map { it.currencyCode }

  val currencyOrDefault: Flow<Currency> =
      currencyCodeOrEmpty.map {
        if (it.isEmpty()) {
          Currency.getInstance(ULocale.getDefault())
        } else {
          Currency.getInstance(it)
        }
      }

  suspend fun setCurrencyCode(
      code: String,
  ): Unit = settingsDataSource.setCurrencyCode(code)

  suspend fun setAgreedToTerms(): Unit = settingsDataSource.setAgreedToTerms()
}
