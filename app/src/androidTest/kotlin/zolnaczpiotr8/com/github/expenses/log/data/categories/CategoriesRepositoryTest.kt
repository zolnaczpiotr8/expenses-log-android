package zolnaczpiotr8.com.github.expenses.log.data.categories

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import java.util.UUID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category

private const val CURRENCY_CODE = "USD"

class CategoriesRepositoryTest {

  private lateinit var database: ExpensesLogDatabase

  private lateinit var categoryDao: CategoryDao
  private lateinit var expenseDao: ExpenseDao
  private lateinit var settingsRepository: SettingsRepository

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    categoryDao = database.categoryDao()
    expenseDao = database.expenseDao()
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
  fun categoryTitles() = runTest {
    val subject =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler)),
        )

    val category = "Category"
    subject.create(category)
    advanceUntilIdle()

    val actual = subject.categoriesTitles().first()
    val expected = listOf(category)

    assert(actual == expected)
  }

  @Test
  fun categoriesSummary() = runTest {
    settingsRepository.setCurrencyCode(CURRENCY_CODE)
    advanceUntilIdle()

    val subject =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler)),
        )

    val category = "Category"
    val categoryUuid = UUID.randomUUID()
    subject.create(title = category, uuid = categoryUuid)
    advanceUntilIdle()

    expenseDao.insert(title = "Expense", amount = 10.0, category = category)
    advanceUntilIdle()

    val actual = subject.categories().first()
    val expected =
        CategoriesSummary(
            totalAmount = CurrencyAmount(10.0, Currency.getInstance(CURRENCY_CODE)),
            categories =
                listOf(
                    Category(
                        totalAmount = CurrencyAmount(10.0, Currency.getInstance(CURRENCY_CODE)),
                        title = category,
                        uuid = categoryUuid,
                    )
                ),
        )

    assert(expected == actual)
  }

  @Test
  fun delete() = runTest {
    val subject =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = StandardTestDispatcher(testScheduler)),
        )

    val category = "Category"
    val uuid = UUID.randomUUID()
    subject.create(title = category, uuid = uuid)
    advanceUntilIdle()

    subject.delete(uuid = uuid)
    advanceUntilIdle()

    val actual = subject.categoriesTitles().first()

    assert(actual.isEmpty())
  }
}
