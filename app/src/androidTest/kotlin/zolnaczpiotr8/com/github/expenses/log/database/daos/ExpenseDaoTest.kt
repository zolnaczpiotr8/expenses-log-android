package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseWithCategoryEntity

class ExpenseDaoTest {

  private lateinit var database: ExpensesLogDatabase

  private lateinit var subject: ExpenseDao
  private lateinit var categoryDao: CategoryDao

  private lateinit var dateFilterDao: DateFilterDao

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    subject = database.expenseDao()
    categoryDao = database.categoryDao()
    dateFilterDao = database.dateFilterDao()
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun initiallyIsEmpty() = runTest {
    val actual = subject.expenses().first()

    assert(actual.isEmpty())
  }

  @Test
  fun onInsertIsInserted() = runTest {
    val categoryTitle = "Food"
    categoryDao.insert(CategoryEntity(title = categoryTitle, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expenseTitle = "Title"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now()
    subject.insert(
        title = expenseTitle,
        amount = amount,
        category = categoryTitle,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    val actual = subject.expenses().first().first()

    assert(actual.title == expenseTitle)
    assert(actual.uuid == uuid)
    assert(actual.amount == amount)
    assert(actual.categoryTitle == categoryTitle)
    assert(actual.created.epochSecond == created.epochSecond)
  }

  @Test
  fun whenMultipleInsertedThenOrdered() = runTest {
    val categoryTitle = "Food"
    categoryDao.insert(CategoryEntity(title = categoryTitle, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expenseTitle = "Title"
    subject.insert(
        title = expenseTitle,
        amount = 10.0,
        category = categoryTitle,
        created = Instant.now().minus(11, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val expenseTitle1 = "Title1"
    subject.insert(
        title = expenseTitle1,
        amount = 10.0,
        category = categoryTitle,
        created = Instant.now().minus(10, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val expenseTitle2 = "Title2"
    subject.insert(
        title = expenseTitle2,
        amount = 10.0,
        category = categoryTitle,
        created = Instant.now().minus(9, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val expenseTitle3 = "Title3"
    subject.insert(
        title = expenseTitle3,
        amount = 10.0,
        category = categoryTitle,
        created = Instant.now().minus(8, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val expenseTitle4 = "Title3"
    subject.insert(
        title = expenseTitle4,
        amount = 10.0,
        category = categoryTitle,
        created = Instant.now().minus(1, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val actual = subject.expenses().first().map(ExpenseWithCategoryEntity::title)
    val expected = listOf(expenseTitle4, expenseTitle3, expenseTitle2, expenseTitle1, expenseTitle)

    assert(actual == expected)
  }

  @Test
  fun onDeleteIsDeleted() = runTest {
    val categoryTitle = "Food"
    categoryDao.insert(CategoryEntity(title = categoryTitle, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expenseTitle = "Title"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now()
    subject.insert(
        title = expenseTitle,
        amount = amount,
        category = categoryTitle,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    subject.delete(uuid)
    advanceUntilIdle()
    val actual = subject.expenses().first()

    assert(actual.isEmpty())
  }

  @Test
  fun whenExpenseIsBelowDateRangeThenNotReturned() = runTest {
    val categoryTitle = "Food"
    categoryDao.insert(CategoryEntity(title = categoryTitle, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expenseTitle = "Title"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now().minus(32, ChronoUnit.DAYS)
    subject.insert(
        title = expenseTitle,
        amount = amount,
        category = categoryTitle,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    val actual = subject.expenses().first()

    assert(actual.isEmpty())
  }

  @Test
  fun whenExpenseIsAboveDateRangeThenNotReturned() = runTest {
    val categoryTitle = "Food"
    categoryDao.insert(CategoryEntity(title = categoryTitle, uuid = UUID.randomUUID()))
    advanceUntilIdle()

    val expenseTitle = "Title"
    val amount = 10.0
    val uuid = UUID.randomUUID()
    val created = Instant.now().plus(32, ChronoUnit.DAYS)
    subject.insert(
        title = expenseTitle,
        amount = amount,
        category = categoryTitle,
        uuid = uuid,
        created = created,
    )
    advanceUntilIdle()

    val actual = subject.expenses().first()

    assert(actual.isEmpty())
  }
}
