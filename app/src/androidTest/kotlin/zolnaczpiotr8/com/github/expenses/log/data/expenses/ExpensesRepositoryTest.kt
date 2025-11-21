package zolnaczpiotr8.com.github.expenses.log.data.expenses

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.data.SettingsRepository
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.datastore.FakeDataStore
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsDataSource
import zolnaczpiotr8.com.github.expenses.log.datastore.SettingsMapper
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Expense

class ExpensesRepositoryTest {

  lateinit var database: ExpensesLogDatabase
  lateinit var subject: ExpensesRepository
  lateinit var categoryDao: CategoryDao

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    categoryDao = database.categoryDao()
    subject =
        ExpensesRepository(
            expenseDao = database.expenseDao(),
            expenseMapper = ExpenseMapper(),
            settingsRepository =
                SettingsRepository(
                    settingsDataSource =
                        SettingsDataSource(
                            dataStore = FakeDataStore(SettingsProto.getDefaultInstance()),
                            settingsMapper = SettingsMapper(),
                        )
                ),
        )
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun whenInsertThenInserted() = runTest {
    val category = "Category"
    categoryDao.insert(CategoryEntity(title = category, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expense = "Expense"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now()
    subject.create(
        title = expense,
        amount = amount,
        category = category,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    val actual = subject.expenses().first()
    val expected =
        listOf(
            Expense(
                title = expense,
                amount = CurrencyAmount(amount, Currency.getInstance(ULocale.getDefault())),
                uuid = uuid,
                created = created.atZone(ZoneId.systemDefault()).toLocalDate(),
                categoryTitle = category,
            )
        )

    assert(actual == expected)
  }

  @Test
  fun whenDeleteThenDeleted() = runTest {
    val category = "Category"
    categoryDao.insert(CategoryEntity(title = category, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expense = "Expense"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now()
    subject.create(
        title = expense,
        amount = amount,
        category = category,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    subject.delete(uuid)

    val actual = subject.expenses().first()

    assert(actual.isEmpty())
  }
}
