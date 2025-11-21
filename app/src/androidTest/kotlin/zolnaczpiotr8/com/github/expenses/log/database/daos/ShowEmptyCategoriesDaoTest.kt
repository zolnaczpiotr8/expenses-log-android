package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.entities.ShowEmptyCategoriesEntity

class ShowEmptyCategoriesDaoTest {

  private lateinit var unitUnderTest: ShowEmptyCategoriesDao
  private lateinit var database: ExpensesLogDatabase

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    unitUnderTest = database.showEmptyCategoriesDao()
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun initiallyItsNull() = runTest {
    val actual = unitUnderTest.showEmptyCategories().first()

    assert(actual == null)
  }

  @Test
  fun onInsertItsInserted() = runTest {
    val input = ShowEmptyCategoriesEntity(value = true)
    unitUnderTest.insert(input)
    advanceUntilIdle()

    val actual = unitUnderTest.showEmptyCategories().first()

    assert(actual == input)
  }

  @Test
  fun onTwoInsertItsReplaced() = runTest {
    val input = ShowEmptyCategoriesEntity(value = true)
    unitUnderTest.insert(input)
    advanceUntilIdle()

    val input2 = ShowEmptyCategoriesEntity(value = true)
    unitUnderTest.insert(input2)
    advanceUntilIdle()

    val actual = unitUnderTest.showEmptyCategories().first()

    assert(actual == input2)
  }
}
