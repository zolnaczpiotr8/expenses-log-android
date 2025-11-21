package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import java.time.Instant
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity

class DateFilterDaoTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var subject: DateFilterDao

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    subject = database.dateFilterDao()
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun initiallyIsNull() = runTest {
    val actual = subject.dateFilter().first()

    assert(actual == null)
  }

  @Test
  fun onInsertIsInserted() = runTest {
    val input = DateFilterEntity(title = "title", start = Instant.now(), finish = Instant.now())

    subject.insert(input)
    advanceUntilIdle()

    val actual = subject.dateFilter().first()

    assert(
        (actual?.title == input.title) and
            (actual?.start?.epochSecond == input.start?.epochSecond) and
            (actual?.finish?.epochSecond == input.finish?.epochSecond)
    )
  }

  @Test
  fun onTwoInsertedIsReplaced() = runTest {
    val input = DateFilterEntity(title = "title", start = Instant.now(), finish = Instant.now())

    subject.insert(input)
    advanceUntilIdle()

    val input2 = DateFilterEntity(title = "title", start = null, finish = null)

    subject.insert(input2)
    advanceUntilIdle()

    val actual = subject.dateFilter().first()

    assert(actual == input2)
  }
}
