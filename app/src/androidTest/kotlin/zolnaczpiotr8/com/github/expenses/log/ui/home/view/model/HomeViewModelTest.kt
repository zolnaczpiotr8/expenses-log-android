package zolnaczpiotr8.com.github.expenses.log.ui.home.view.model

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import java.time.LocalDate
import java.util.UUID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.data.ShowEmptyCategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoriesRepository
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoryAggregator
import zolnaczpiotr8.com.github.expenses.log.data.date.filter.DateFilterEntityMapper
import zolnaczpiotr8.com.github.expenses.log.data.date.filter.DateFilterMapper
import zolnaczpiotr8.com.github.expenses.log.data.date.filter.DateFilterRepository
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpenseMapper
import zolnaczpiotr8.com.github.expenses.log.data.expenses.ExpensesRepository
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class HomeViewModelTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var settingsRepository: SettingsRepository
  private lateinit var expensesRepository: ExpensesRepository
  private lateinit var showEmptyCategoriesRepository: ShowEmptyCategoriesRepository
  private lateinit var dateFilterRepository: DateFilterRepository
  private lateinit var categoryDao: CategoryDao

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
    showEmptyCategoriesRepository =
        ShowEmptyCategoriesRepository(showEmptyCategoriesDao = database.showEmptyCategoriesDao())
    dateFilterRepository =
        DateFilterRepository(
            dateFilterDao = database.dateFilterDao(),
            dateFilterMapper = DateFilterMapper(),
            dateFilterEntityMapper = DateFilterEntityMapper(),
        )
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun expenseDelete() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    val category = "Category"
    val categoryUuid = UUID.randomUUID()
    categoriesRepository.create(title = category, uuid = categoryUuid)
    advanceUntilIdle()

    val expenseUuid = UUID.randomUUID()
    expensesRepository.create(title = null, uuid = expenseUuid, amount = 10.0, category = category)
    advanceUntilIdle()

    subject.onExpenseDeleteClicked(expenseUuid)
    advanceUntilIdle()

    val actualExpenses = expensesRepository.expenses().first()

    assert(actualExpenses.isEmpty())
  }

  @Test
  fun categoryDelete() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    val category = "Category"
    val categoryUuid = UUID.randomUUID()
    categoriesRepository.create(title = category, uuid = categoryUuid)
    advanceUntilIdle()

    subject.onCategoryDeleteClicked(categoryUuid)
    advanceUntilIdle()

    val actualCategories = categoriesRepository.categoriesTitles().first()

    assert(actualCategories.isEmpty())
  }

  @Test
  fun showEmptyCategories() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.onShowEmptyCategoriesFilterClick(true)
    advanceUntilIdle()

    val showEmptyCategories = showEmptyCategoriesRepository.showEmptyCategory().first()

    assert(showEmptyCategories)
  }

  @Test
  fun onDateFilterClick() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    val expectedDateFilter = DateFilter.Any
    subject.onDateFilterClick(expectedDateFilter)
    advanceUntilIdle()

    val actualDateFilter = dateFilterRepository.dateFilter().first()

    assert(actualDateFilter == expectedDateFilter)
  }

  @Test
  fun onAgreeToTermsClick() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.onAgreeToTermsClick()
    advanceUntilIdle()

    val agreedToTerms = settingsRepository.settings.first().agreedToTerms

    assert(agreedToTerms)
  }

  @Test
  fun showEmptyCategoriesFilter() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.showEmptyCategoriesFilter.test {
      val actualDefault = awaitItem()
      val expectedDefault = false

      assert(actualDefault == expectedDefault)

      val expectedShowEmptyCategories = true
      showEmptyCategoriesRepository.set(expectedShowEmptyCategories)
      advanceUntilIdle()

      val actualShowEmptyCategories = awaitItem()
      assert(actualShowEmptyCategories == expectedShowEmptyCategories)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun dateFilter() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.dateFilter.test {
      val expected1 = DateFilter.Month
      val actual1 = awaitItem()

      assert(expected1 == actual1)

      val expected2 = DateFilter.Any
      dateFilterRepository.update(expected2)
      advanceUntilIdle()
      val actual2 = awaitItem()

      assert(actual2 == expected2)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun categories() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    val currency = Currency.getInstance(ULocale.getDefault())
    val category =
        Category(
            uuid = UUID.randomUUID(),
            totalAmount = CurrencyAmount(10, currency),
            title = "Category",
        )
    subject.categoriesSummary.test {
      val expected1 = null
      val actual1 = awaitItem()

      assert(expected1 == actual1)

      val expected2 =
          CategoriesSummary(totalAmount = CurrencyAmount(0, currency), categories = emptyList())
      val actual2 = awaitItem()
      assert(expected2 == actual2)

      categoriesRepository.create(title = category.title, uuid = category.uuid)
      advanceUntilIdle()
      expensesRepository.create(
          title = null,
          category = category.title,
          amount = category.totalAmount.number.toDouble(),
      )
      advanceUntilIdle()
      val expected3 =
          CategoriesSummary(totalAmount = category.totalAmount, categories = listOf(category))
      val actual3 = awaitItem()
      assert(expected3 == actual3)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun expenses() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.expenses.test {
      val expected1 = emptyMap<LocalDate, List<Expense>>()
      val actual1 = awaitItem()

      assert(expected1 == actual1)

      val category = "Category"
      categoriesRepository.create(title = category)
      advanceUntilIdle()

      val expense = "Expense"
      val amount = 10.0
      val uuid = UUID.randomUUID()
      expensesRepository.create(title = expense, amount = amount, category = category, uuid = uuid)
      advanceUntilIdle()

      val created = LocalDate.now()
      val currency = Currency.getInstance(ULocale.getDefault())
      val expected2 =
          mapOf(
              created to
                  listOf(
                      Expense(
                          created = LocalDate.now(),
                          uuid = uuid,
                          title = expense,
                          categoryTitle = category,
                          amount = CurrencyAmount(amount, currency),
                      )
                  )
          )
      val actual2 = awaitItem()

      assert(expected2 == actual2)

      ensureAllEventsConsumed()
    }
  }

  @Test
  fun agreedToTerms() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )

    val subject =
        HomeViewModel(
            categoriesRepository = categoriesRepository,
            expensesRepository = expensesRepository,
            showEmptyCategoriesRepository = showEmptyCategoriesRepository,
            dateFilterRepository = dateFilterRepository,
            settingsRepository = settingsRepository,
            expensesTransformer = ExpensesByDateTransformer(dispatcher = dispatcher),
            coroutineDispatcher = dispatcher,
        )

    subject.agreedToTerms.test {
      val expected1 = true
      val actual1 = awaitItem()

      assert(expected1 == actual1)

      val expected2 = false
      val actual2 = awaitItem()

      assert(expected2 == actual2)

      settingsRepository.setAgreedToTerms()
      advanceUntilIdle()
      val actual3 = awaitItem()
      assert(actual3)

      ensureAllEventsConsumed()
    }
  }
}
