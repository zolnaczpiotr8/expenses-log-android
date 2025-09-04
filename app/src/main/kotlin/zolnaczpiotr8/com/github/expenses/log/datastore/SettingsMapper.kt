package zolnaczpiotr8.com.github.expenses.log.datastore

import javax.inject.Inject
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsMapper @Inject constructor() {

  fun map(
      settings: SettingsProto,
  ): Settings =
      with(settings) { Settings(agreedToTerms = agreedToTerms, currencyCode = currencyCode) }
}
