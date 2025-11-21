package zolnaczpiotr8.com.github.expenses.log.database.converters

import java.util.UUID
import org.junit.Test

private const val UUID_STRING = "56c89888-e904-4c70-b3af-9f9aa5029f40"

class UuidConverterTest {

  private val unitUnderTest = UuidConverter()

  @Test
  fun givenLowercaseWhenFromUuidThenUppercase() {
    assert(unitUnderTest.fromUuid(UUID.fromString(UUID_STRING)) == UUID_STRING)
  }

  @Test
  fun givenNullWhenFromThenNull() {
    assert(unitUnderTest.fromUuid(null) == null)
  }

  @Test
  fun fromTest() {
    assert(unitUnderTest.fromUuid(UUID.fromString(UUID_STRING)) == UUID_STRING)
  }

  @Test
  fun toTest() {
    assert(unitUnderTest.toUuid(UUID_STRING) == UUID.fromString(UUID_STRING))
  }

  @Test
  fun givenNullWhenToThenNull() {
    assert(unitUnderTest.toUuid(null) == null)
  }
}
