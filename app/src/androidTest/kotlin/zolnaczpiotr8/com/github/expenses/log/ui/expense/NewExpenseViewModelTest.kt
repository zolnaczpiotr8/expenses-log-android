package zolnaczpiotr8.com.github.expenses.log.ui.expense

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
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
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoryAggregator
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpenseMapper
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto

class NewExpenseViewModelTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var categoryDao: CategoryDao
  private lateinit var settingsRepository: SettingsRepository
  private lateinit var expensesRepository: ExpensesRepository

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    categoryDao = database.categoryDao()
    settingsRepository =
        SettingsRepository(
            settingsDataSource =
                SettingsDataSource(
                    dataStore = FakeDataStore(SettingsProto.getDefaultInstance()),
                    settingsMapper = SettingsMapper(),
                )
        )
    expensesRepository =
        ExpensesRepository(
            expenseDao = database.expenseDao(),
            expenseMapper = ExpenseMapper(),
            settingsRepository = settingsRepository,
        )
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun whenSaveThenSaved() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewExpenseViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            settingsRepository = settingsRepository,
            coroutineDispatcher = dispatcher,
        )

    val expenseTitle = "Expense"
    val amount = 10.0
    val category = "Category"
    val currencyCode = ""

    subject.save(
        title = expenseTitle,
        amount = amount,
        category = category,
        currencyCode = currencyCode,
    )
    advanceUntilIdle()

    val actualCategories = categoriesRepository.categoriesTitles().first()
    val expectedCategories = listOf(category)
    val actualCurrency = settingsRepository.currencyCodeOrEmpty.first()
    val expectedAmount = CurrencyAmount(amount, Currency.getInstance(ULocale.getDefault()))
    val actualExpense = expensesRepository.expenses().first().first()
    val actualExpenseTitle = actualExpense.title
    val actualExpenseAmount = actualExpense.amount

    assert(actualCategories == expectedCategories)
    assert(actualCurrency == expectedAmount.currency.currencyCode)
    assert(actualExpenseTitle == expenseTitle)
    assert(actualExpenseAmount == expectedAmount)
  }

  @Test
  fun givenNotEmptyCurrencyWhenSaveThenNotSaved() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewExpenseViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            settingsRepository = settingsRepository,
            coroutineDispatcher = dispatcher,
        )

    val expenseTitle = "Expense"
    val amount = 10.0
    val category = "Category"
    val currencyCode = "USD"

    subject.save(
        title = expenseTitle,
        amount = amount,
        category = category,
        currencyCode = currencyCode,
    )
    advanceUntilIdle()

    val actualCurrency = settingsRepository.currencyCodeOrEmpty.first()

    assert(actualCurrency.isEmpty())
  }

  @Test
  fun givenBlankExpenseTitleWhenSaveThenTitleNull() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewExpenseViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            settingsRepository = settingsRepository,
            coroutineDispatcher = dispatcher,
        )

    val expenseTitle = "    "
    val amount = 10.0
    val category = "Category"
    val currencyCode = "USD"

    subject.save(
        title = expenseTitle,
        amount = amount,
        category = category,
        currencyCode = currencyCode,
    )
    advanceUntilIdle()

    val actualExpenseTitle = expensesRepository.expenses().first().first().title

    assert(actualExpenseTitle == null)
  }

  @Test
  fun currencyCode() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewExpenseViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            settingsRepository = settingsRepository,
            coroutineDispatcher = dispatcher,
        )

    subject.currencyCode.test {
      val default = awaitItem()
      assert(default.isEmpty())

      val usd = "USD"
      settingsRepository.setCurrencyCode(usd)
      advanceUntilIdle()

      val usdCurrency = awaitItem()
      assert(usdCurrency == usd)

      val pln = "PLN"
      settingsRepository.setCurrencyCode(pln)
      advanceUntilIdle()

      val plnCurrency = awaitItem()
      assert(pln == plnCurrency)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun categoriesTitle() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewExpenseViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            settingsRepository = settingsRepository,
            coroutineDispatcher = dispatcher,
        )

    subject.categoriesTitles.test {
      val default = awaitItem()
      assert(default.isEmpty())

      val newCategory1 = "AAA"
      categoriesRepository.create(newCategory1)
      advanceUntilIdle()

      val actualCategories1 = awaitItem()
      val expectedCategories1 = listOf(newCategory1)
      assert(actualCategories1 == expectedCategories1)

      val newCategory2 = "BAAA"
      categoriesRepository.create(newCategory2)
      advanceUntilIdle()

      val actualCategories2 = awaitItem()
      val expectedCategories2 = listOf(newCategory1, newCategory2)
      assert(actualCategories2 == expectedCategories2)

      val newCategory3 = "CAAA"
      categoriesRepository.create(newCategory3)
      advanceUntilIdle()

      val actualCategories3 = awaitItem()
      val expectedCategories3 = listOf(newCategory1, newCategory2, newCategory3)
      assert(actualCategories3 == expectedCategories3)

      ensureAllEventsConsumed()
    }
  }
}
