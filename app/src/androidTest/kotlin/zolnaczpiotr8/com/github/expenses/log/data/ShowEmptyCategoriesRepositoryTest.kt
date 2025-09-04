package zolnaczpiotr8.com.github.expenses.log.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase

class ShowEmptyCategoriesRepositoryTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var subject: ShowEmptyCategoriesRepository

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    subject =
        ShowEmptyCategoriesRepository(showEmptyCategoriesDao = database.showEmptyCategoriesDao())
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun initiallyIsFalse() = runTest {
    val actual = subject.showEmptyCategory().first()

    assert(actual.not())
  }

  @Test
  fun onSetIsChanged() = runTest {
    subject.set(true)
    advanceUntilIdle()

    val actual = subject.showEmptyCategory().first()

    assert(actual)
  }
}
