package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterRepositoryTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var subject: DateFilterRepository

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    subject =
        DateFilterRepository(
            dateFilterDao = database.dateFilterDao(),
            dateFilterMapper = DateFilterMapper(),
            dateFilterEntityMapper = DateFilterEntityMapper(),
        )
  }

  @Test
  fun initiallyIsDefault() = runTest {
    val actual = subject.dateFilter().first()
    val expected = DateFilter.Default

    assert(actual == expected)
  }

  @Test
  fun onUpdateIsChanged() = runTest {
    val input = DateFilter.Any
    subject.update(input)
    advanceUntilIdle()

    val actual = subject.dateFilter().first()

    assert(actual == input)
  }

  @After
  fun cleanup() {
    database.close()
  }
}
