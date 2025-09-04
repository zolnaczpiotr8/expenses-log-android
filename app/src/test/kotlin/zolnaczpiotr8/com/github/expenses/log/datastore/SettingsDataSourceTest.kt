package zolnaczpiotr8.com.github.expenses.log.datastore

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto

class SettingsDataSourceTest {

  lateinit var unitUnderTest: SettingsDataSource

  @Before
  fun setup() {
    unitUnderTest =
        SettingsDataSource(
            dataStore = FakeDataStore(SettingsProto.getDefaultInstance()),
            settingsMapper = SettingsMapper(),
        )
  }

  @Test
  fun currencyByDefaultIsEmpty() = runTest {
    val actual = unitUnderTest.settings.first().currencyCode

    assert(actual.isEmpty())
  }

  @Test
  fun currencyChangesOnSet() = runTest {
    val usdCurrency = "USD"
    unitUnderTest.setCurrencyCode(usdCurrency)
    advanceUntilIdle()

    val actual = unitUnderTest.settings.first().currencyCode

    assert(actual == usdCurrency)
  }

  @Test
  fun agreedToTermsChangesOnSet() = runTest {
    unitUnderTest.setAgreedToTerms()
    advanceUntilIdle()

    val actual = unitUnderTest.settings.first().agreedToTerms

    assert(actual)
  }

  @Test
  fun agreedToTermsByDefaultIsEmpty() = runTest {
    val actual = unitUnderTest.settings.first().agreedToTerms

    assert(actual.not())
  }
}
