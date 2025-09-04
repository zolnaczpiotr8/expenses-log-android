package zolnaczpiotr8.com.github.expenses.log.ui.settings

import android.icu.util.Currency
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto

class SettingsViewModelTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var settingsRepository: SettingsRepository

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    settingsRepository =
        SettingsRepository(
            settingsDataSource =
                SettingsDataSource(
                    dataStore = FakeDataStore(SettingsProto.getDefaultInstance()),
                    settingsMapper = SettingsMapper(),
                )
        )
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun onCurrencyClick() = runTest {
    val subject =
        SettingsViewModel(
            settingsRepository = settingsRepository,
            coroutineDispatcher = StandardTestDispatcher(testScheduler),
        )

    val currency = "USD"
    subject.onCurrencyClick(code = currency)
    advanceUntilIdle()

    val actualCurrency = settingsRepository.currencyCodeOrEmpty.first()

    assert(actualCurrency == currency)
  }

  @Test
  fun currentCurrency() = runTest {
    val subject =
        SettingsViewModel(
            settingsRepository = settingsRepository,
            coroutineDispatcher = StandardTestDispatcher(testScheduler),
        )

    subject.currentCurrency.test {
      val expected1 = ""
      val actual1 = awaitItem()

      assert(expected1 == actual1)

      val expected2 = "USD"
      settingsRepository.setCurrencyCode(expected2)
      advanceUntilIdle()
      val actual2 = awaitItem()

      assert(expected2 == actual2)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun availableCurrencies() = runTest {
    val subject =
        SettingsViewModel(
            settingsRepository = settingsRepository,
            coroutineDispatcher = StandardTestDispatcher(testScheduler),
        )

    subject.availableCurrencies.test {
      val actual = awaitItem()
      assert(actual.isEmpty())

      val expected1 = Currency.getAvailableCurrencies().map(Currency::getCurrencyCode).sorted()
      val actual1 = awaitItem()
      assert(expected1 == actual1)

      ensureAllEventsConsumed()
    }
  }
}
