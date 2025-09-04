package zolnaczpiotr8.com.github.expenses.log.data

import android.icu.util.Currency
import android.icu.util.ULocale
import java.util.Locale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsRepositoryTest {

  lateinit var unitUnderTest: SettingsRepository

  @Before
  fun setup() {
    unitUnderTest =
        SettingsRepository(
            settingsDataSource =
                SettingsDataSource(
                    dataStore = FakeDataStore(SettingsProto.getDefaultInstance()),
                    settingsMapper = SettingsMapper(),
                )
        )
  }

  @Test
  fun defaultSettings() = runTest {
    val actual = unitUnderTest.settings.first()

    val expected = Settings(agreedToTerms = false, currencyCode = "")

    assert(actual == expected)
  }

  @Test
  fun initiallyCurrencyCodeIsEmpty() = runTest {
    val actual = unitUnderTest.currencyCodeOrEmpty.first()

    assert(actual.isEmpty())
  }

  @Test
  fun currencyCodeChangesOnSet() = runTest {
    val currencyCode = "USD"
    unitUnderTest.setCurrencyCode(currencyCode)
    advanceUntilIdle()

    val actual = unitUnderTest.currencyCodeOrEmpty.first()

    assert(actual == currencyCode)
  }

  @Test
  fun defaultCurrency() = runTest {
    Locale.setDefault(Locale.UK)
    val actual = unitUnderTest.currencyOrDefault.first()

    val expected = Currency.getInstance(ULocale.UK)

    assert(actual == expected)
  }

  @Test
  fun currencyChangesOnSet() = runTest {
    val currencyCode = "USD"
    unitUnderTest.setCurrencyCode(currencyCode)
    advanceUntilIdle()

    val actual = unitUnderTest.currencyOrDefault.first()

    val expected = Currency.getInstance(currencyCode)

    assert(actual == expected)
  }

  @Test
  fun agreedToTermsChangesOnSet() = runTest {
    unitUnderTest.setAgreedToTerms()
    advanceUntilIdle()

    val actual = unitUnderTest.settings.first()

    val expected = Settings(agreedToTerms = true, currencyCode = "")

    assert(actual == expected)
  }
}
