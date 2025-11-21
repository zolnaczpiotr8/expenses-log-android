package zolnaczpiotr8.com.github.expenses.log.ui.category

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
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
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto

class NewCategoryViewModelTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var categoryDao: CategoryDao
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
  fun whenCreateThenCreated() = runTest {
    val dispatcher = StandardTestDispatcher(testScheduler)
    val categoriesRepository =
        CategoriesRepository(
            settingsRepository = settingsRepository,
            categoryDao = categoryDao,
            aggregator = CategoryAggregator(dispatcher = dispatcher),
        )
    val subject =
        NewCategoryViewModel(
            categoriesRepository = categoriesRepository,
            coroutineDispatcher = dispatcher,
        )

    val category = "Category"
    subject.create(category)
    advanceUntilIdle()

    val actual = categoryDao.categoriesTitles().first()
    val expected = listOf(category)

    assert(actual == expected)
  }
}
