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
import zolnaczpiotr8.com.github.expenses.log.database.entities.ShowEmptyCategoriesEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity

class CategoryDaoTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var subject: CategoryDao

  private lateinit var expenseDao: ExpenseDao

  private lateinit var showEmptyCategoriesDao: ShowEmptyCategoriesDao

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    subject = database.categoryDao()
    expenseDao = database.expenseDao()
    showEmptyCategoriesDao = database.showEmptyCategoriesDao()
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun categoriesTitlesInitiallyEmpty() = runTest {
    val actual = subject.categoriesTitles().first()

    assert(actual.isEmpty())
  }

  @Test
  fun categoriesInitiallyEmpty() = runTest {
    val actual = subject.categories().first()

    assert(actual.isEmpty())
  }

  @Test
  fun onInsertIsInserted() = runTest {
    val input = CategoryEntity(uuid = UUID.randomUUID(), title = "Title")

    subject.insert(input)
    advanceUntilIdle()

    val actual = subject.categoriesTitles().first()
    val expected = listOf(input.title)

    assert(actual == expected)
  }

  @Test
  fun titlesAreOrdered() = runTest {
    subject.insert(CategoryEntity(uuid = UUID.randomUUID(), title = "B"))
    advanceUntilIdle()

    subject.insert(CategoryEntity(uuid = UUID.randomUUID(), title = "A"))
    advanceUntilIdle()

    subject.insert(CategoryEntity(uuid = UUID.randomUUID(), title = "Z"))
    advanceUntilIdle()

    subject.insert(CategoryEntity(uuid = UUID.randomUUID(), title = "C"))
    advanceUntilIdle()

    subject.insert(CategoryEntity(uuid = UUID.randomUUID(), title = "F"))
    advanceUntilIdle()

    val actual = subject.categoriesTitles().first()
    val expected = listOf("A", "B", "C", "F", "Z")

    assert(actual == expected)
  }

  @Test
  fun whenInsertExistingCategoryThenIgnored() = runTest {
    val input = CategoryEntity(uuid = UUID.randomUUID(), title = "Title")

    subject.insert(input)
    advanceUntilIdle()

    val input2 = CategoryEntity(uuid = UUID.randomUUID(), title = input.title)

    subject.insert(input2)
    advanceUntilIdle()
    val actual = subject.categoriesTitles().first()
    val expected = listOf(input.title)

    assert(actual == expected)
  }

  @Test
  fun onDeleteIsDeleted() = runTest {
    val input = CategoryEntity(uuid = UUID.randomUUID(), title = "Title")

    subject.insert(input)
    advanceUntilIdle()

    subject.delete(input.uuid)

    val actual = subject.categoriesTitles().first()

    assert(actual.isEmpty())
  }

  @Test
  fun onDeleteAllExpensesAreAlsoDeleted() = runTest {
    val input = CategoryEntity(uuid = UUID.randomUUID(), title = "Title")

    subject.insert(input)
    advanceUntilIdle()

    expenseDao.insert(
        title = "Expense",
        amount = 10.0,
        category = input.title,
    )
    advanceUntilIdle()

    subject.delete(input.uuid)
    advanceUntilIdle()

    val actual = expenseDao.expenses().first()

    assert(actual.isEmpty())
  }

  @Test
  fun categoriesSummaryIsOrdered() = runTest {
    val category3 = CategoryEntity(uuid = UUID.randomUUID(), title = "ZCategory3")

    subject.insert(category3)
    advanceUntilIdle()

    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val category2 = CategoryEntity(uuid = UUID.randomUUID(), title = "BBBCategory2")

    subject.insert(category2)
    advanceUntilIdle()

    val expense3 = "Expense3"
    expenseDao.insert(
        title = expense3,
        amount = 10.0,
        category = category3.title,
    )
    advanceUntilIdle()

    val expense1 = "Expense1"
    expenseDao.insert(
        title = expense1,
        amount = 10.0,
        category = category1.title,
    )
    advanceUntilIdle()

    val expense2 = "Expense2"
    expenseDao.insert(
        title = expense2,
        amount = 10.0,
        category = category2.title,
    )
    advanceUntilIdle()

    val actual = subject.categories().first().map(CategoryTotalEntity::title)
    val expected = listOf(category1.title, category2.title, category3.title)

    assert(actual == expected)
  }

  @Test
  fun emptyCategoriesNotReturnedByDefault() = runTest {
    val category3 = CategoryEntity(uuid = UUID.randomUUID(), title = "ZCategory3")

    subject.insert(category3)
    advanceUntilIdle()

    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val category2 = CategoryEntity(uuid = UUID.randomUUID(), title = "BBBCategory2")

    subject.insert(category2)
    advanceUntilIdle()

    val expense1 = "Expense1"
    expenseDao.insert(
        title = expense1,
        amount = 10.0,
        category = category1.title,
    )
    advanceUntilIdle()

    val expense2 = "Expense2"
    expenseDao.insert(
        title = expense2,
        amount = 10.0,
        category = category2.title,
    )
    advanceUntilIdle()

    val actual = subject.categories().first().map(CategoryTotalEntity::title)
    val expected = listOf(category1.title, category2.title)

    assert(actual == expected)
  }

  @Test
  fun sum() = runTest {
    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val category2 = CategoryEntity(uuid = UUID.randomUUID(), title = "BBBCategory2")

    subject.insert(category2)
    advanceUntilIdle()

    val expense3 = "Expense3"
    expenseDao.insert(
        title = expense3,
        amount = 10.0,
        category = category2.title,
    )
    advanceUntilIdle()

    val expense1 = "Expense1"
    expenseDao.insert(
        title = expense1,
        amount = 10.0,
        category = category1.title,
    )
    advanceUntilIdle()

    val expense2 = "Expense2"
    expenseDao.insert(
        title = expense2,
        amount = 10.0,
        category = category2.title,
    )
    advanceUntilIdle()

    val actual = subject.categories().first().map(CategoryTotalEntity::totalAmount)
    val expected = listOf(10.0, 20.0)

    assert(actual == expected)
  }

  @Test
  fun whenShowEmptyCategoriesIsTrue() = runTest {
    showEmptyCategoriesDao.insert(ShowEmptyCategoriesEntity(value = true))
    advanceUntilIdle()

    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val category2 = CategoryEntity(uuid = UUID.randomUUID(), title = "BBBCategory2")

    subject.insert(category2)
    advanceUntilIdle()

    val actual = subject.categories().first()
    val expected =
        listOf(
            CategoryTotalEntity(uuid = category1.uuid, title = category1.title, totalAmount = 0.0),
            CategoryTotalEntity(uuid = category2.uuid, title = category2.title, totalAmount = 0.0),
        )

    assert(actual == expected)
  }

  @Test
  fun isExpenseIsBelowDateRangeThenIsNotReturned() = runTest {
    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val expense1 = "Expense1"
    expenseDao.insert(
        title = expense1,
        amount = 10.0,
        category = category1.title,
    )
    advanceUntilIdle()

    val expense2 = "Expense2"
    expenseDao.insert(
        title = expense2,
        amount = 7.0,
        category = category1.title,
        created = Instant.now().minus(32, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val actual = subject.categories().first()
    val expected =
        listOf(
            CategoryTotalEntity(uuid = category1.uuid, title = category1.title, totalAmount = 10.0)
        )

    assert(expected == actual)
  }

  @Test
  fun isExpenseIsAboveDateRangeThenIsNotReturned() = runTest {
    val category1 = CategoryEntity(uuid = UUID.randomUUID(), title = "AACategory1")

    subject.insert(category1)
    advanceUntilIdle()

    val expense1 = "Expense1"
    expenseDao.insert(
        title = expense1,
        amount = 10.0,
        category = category1.title,
    )
    advanceUntilIdle()

    val expense2 = "Expense2"
    expenseDao.insert(
        title = expense2,
        amount = 7.0,
        category = category1.title,
        created = Instant.now().plus(32, ChronoUnit.DAYS),
    )
    advanceUntilIdle()

    val actual = subject.categories().first()
    val expected =
        listOf(
            CategoryTotalEntity(uuid = category1.uuid, title = category1.title, totalAmount = 10.0)
        )

    assert(expected == actual)
  }
}
