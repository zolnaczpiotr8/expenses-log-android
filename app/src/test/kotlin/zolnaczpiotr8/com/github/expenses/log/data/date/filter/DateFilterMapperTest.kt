package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import java.time.Instant
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterMapperTest {

  private val unitUnderTest = DateFilterMapper()

  @Test
  fun whenAnyThenAny() {
    val actual = unitUnderTest.toEntity(DateFilter.Any)
    val expected = DateFilterEntity(title = DateFilter.Any.NAME)
    assert(actual == expected)
  }

  @Test
  fun whenMonthThenMonth() {
    val actual = unitUnderTest.toEntity(DateFilter.Month)
    val expected = DateFilterEntity(title = DateFilter.Month.NAME)
    assert(actual == expected)
  }

  @Test
  fun whenYearThenYear() {
    val actual = unitUnderTest.toEntity(DateFilter.Year)
    val expected = DateFilterEntity(title = DateFilter.Year.NAME)
    assert(actual == expected)
  }

  @Test
  fun whenCustomThenCustom() {
    val actual = unitUnderTest.toEntity(DateFilter.Custom(start = Instant.MIN, end = Instant.MAX))
    val expected =
        DateFilterEntity(
            title = DateFilter.Custom.NAME,
            start = Instant.MIN,
            finish = Instant.MAX,
        )
    assert(actual == expected)
  }
}
