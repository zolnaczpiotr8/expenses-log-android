package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import java.time.Instant
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterEntityMapperTest {

  private val unitUnderTest = DateFilterEntityMapper()

  @Test
  fun any() {
    val actual = unitUnderTest.map(DateFilterEntity(title = DateFilter.Any.NAME))
    val expected = DateFilter.Any
    assert(actual == expected)
  }

  @Test
  fun month() {
    val actual = unitUnderTest.map(DateFilterEntity(title = DateFilter.Month.NAME))
    val expected = DateFilter.Month
    assert(actual == expected)
  }

  @Test
  fun year() {
    val actual = unitUnderTest.map(DateFilterEntity(title = DateFilter.Year.NAME))
    val expected = DateFilter.Year
    assert(actual == expected)
  }

  @Test
  fun whenNullThenDefault() {
    val actual = unitUnderTest.map(null)
    val expected = DateFilter.Default
    assert(actual == expected)
  }

  @Test
  fun givenCustomWhenStartNullThenDefault() {
    val actual =
        unitUnderTest.map(
            DateFilterEntity(
                title = DateFilter.Custom.NAME,
                start = null,
                finish = Instant.MAX,
            )
        )
    val expected = DateFilter.Default
    assert(actual == expected)
  }

  @Test
  fun givenCustomWhenFinishNullThenDefault() {
    val actual =
        unitUnderTest.map(
            DateFilterEntity(
                title = DateFilter.Custom.NAME,
                start = Instant.MIN,
                finish = null,
            )
        )
    val expected = DateFilter.Default
    assert(actual == expected)
  }

  @Test
  fun givenCustomWhenFinishAndStartNullThenDefault() {
    val actual =
        unitUnderTest.map(
            DateFilterEntity(title = DateFilter.Custom.NAME, start = null, finish = null)
        )
    val expected = DateFilter.Default
    assert(actual == expected)
  }

  @Test
  fun givenCustomWhenFinishAndStartNoNullThenCustom() {
    val actual =
        unitUnderTest.map(
            DateFilterEntity(
                title = DateFilter.Custom.NAME,
                start = Instant.MIN,
                finish = Instant.MAX,
            )
        )
    val expected = DateFilter.Custom(start = Instant.MIN, end = Instant.MAX)
    assert(actual == expected)
  }
}
