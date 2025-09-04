package zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.DateFilterDao

private const val QUERY = "SELECT * FROM date_filter_time_stamp"
private const val START_COLUMN_NAME = "start"
private const val FINISH_COLUMN_NAME = "finish"

class DateFilterTimeStampEntityTest {

  private lateinit var database: ExpensesLogDatabase
  private lateinit var dateFilterDao: DateFilterDao

  @Before
  fun setup() {
    database =
        Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = ExpensesLogDatabase::class.java,
            )
            .build()
    dateFilterDao = database.dateFilterDao()
  }

  @After
  fun cleanup() {
    database.close()
  }

  @Test
  fun byDefaultIsMonth() {
    val cursor = database.query(query = QUERY, args = null)

    with(cursor) {
      moveToNext()

      val start = getLong(getColumnIndexOrThrow(START_COLUMN_NAME))
      val finish = getLong(getColumnIndexOrThrow(FINISH_COLUMN_NAME))

      val expectedStart =
          LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

      val expectedFinish =
          LocalDate.now()
              .plusMonths(1)
              .withDayOfMonth(1)
              .atStartOfDay(ZoneId.of("UTC"))
              .toEpochSecond()
              .dec()

      assert(cursor.count == 1)
      assert(start == expectedStart)
      assert(finish == expectedFinish)
    }
  }

  @Test
  fun isMonthIsInsertedThenMonth() = runTest {
    dateFilterDao.insert(DateFilterEntity(title = "Month"))
    advanceUntilIdle()

    val cursor = database.query(query = QUERY, args = null)

    with(cursor) {
      moveToNext()

      val start = getLong(getColumnIndexOrThrow(START_COLUMN_NAME))
      val finish = getLong(getColumnIndexOrThrow(FINISH_COLUMN_NAME))

      val expectedStart =
          LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

      val expectedFinish =
          LocalDate.now()
              .plusMonths(1)
              .withDayOfMonth(1)
              .atStartOfDay(ZoneId.of("UTC"))
              .toEpochSecond()
              .dec()

      assert(cursor.count == 1)
      assert(start == expectedStart)
      assert(finish == expectedFinish)
    }
  }

  @Test
  fun ifAnyIsInsertedThenAny() = runTest {
    dateFilterDao.insert(DateFilterEntity(title = "Any"))
    advanceUntilIdle()

    val cursor = database.query(query = QUERY, args = null)

    with(cursor) {
      moveToNext()

      val start = getLong(getColumnIndexOrThrow(START_COLUMN_NAME))
      val finish = getLong(getColumnIndexOrThrow(FINISH_COLUMN_NAME))

      assert(cursor.count == 1)

      assert(start == 0L)
      assert(finish == 9_223_372_036_854_775_807L)
    }
  }

  @Test
  fun isYearIsInsertedThenYear() = runTest {
    dateFilterDao.insert(DateFilterEntity(title = "Year"))
    advanceUntilIdle()

    val cursor = database.query(query = QUERY, args = null)

    with(cursor) {
      moveToNext()

      val start = getLong(getColumnIndexOrThrow(START_COLUMN_NAME))
      val finish = getLong(getColumnIndexOrThrow(FINISH_COLUMN_NAME))

      val expectedStart =
          LocalDate.now().withDayOfYear(1).atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

      val expectedFinish =
          LocalDate.now()
              .plusYears(1)
              .withDayOfYear(1)
              .atStartOfDay(ZoneId.of("UTC"))
              .toEpochSecond()
              .dec()

      assert(cursor.count == 1)
      assert(start == expectedStart)
      assert(finish == expectedFinish)
    }
  }

  @Test
  fun isCustomIsInsertedThenCustom() = runTest {
    dateFilterDao.insert(
        DateFilterEntity(title = "Custom", start = Instant.now(), finish = Instant.now())
    )
    advanceUntilIdle()

    val cursor = database.query(query = QUERY, args = null)

    with(cursor) {
      moveToNext()

      val start = getLong(getColumnIndexOrThrow(START_COLUMN_NAME))
      val finish = getLong(getColumnIndexOrThrow(FINISH_COLUMN_NAME))

      val expectedStart = LocalDate.now().atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

      val expectedFinish =
          LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("UTC")).toEpochSecond().dec()

      assert(cursor.count == 1)
      assert(start == expectedStart)
      assert(finish == expectedFinish)
    }
  }
}
