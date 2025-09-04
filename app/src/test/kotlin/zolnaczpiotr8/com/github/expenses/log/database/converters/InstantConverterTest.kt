package zolnaczpiotr8.com.github.expenses.log.database.converters

import java.time.Instant
import org.junit.Test

private const val SECONDS = 1_757_670_844L
private const val STRING = "2025-09-12T09:54:04Z"

class InstantConverterTest {

  private val unitUnderTest = InstantConverter()

  @Test
  fun `GIVEN null WHEN toInstant THEN null`() {
    assert(unitUnderTest.toInstant(null) == null)
  }

  @Test
  fun `GIVEN null WHEN fromInstant THEN null`() {
    assert(unitUnderTest.fromInstant(null) == null)
  }

  @Test
  fun `GIVEN milliseconds WHEN toInstant THEN correct`() {
    assert(unitUnderTest.toInstant(SECONDS).toString() == STRING)
  }

  @Test
  fun `GIVEN instant WHEN fromInstant THEN correct`() {
    val instant = Instant.parse(STRING)
    assert(unitUnderTest.fromInstant(instant) == SECONDS)
  }
}
