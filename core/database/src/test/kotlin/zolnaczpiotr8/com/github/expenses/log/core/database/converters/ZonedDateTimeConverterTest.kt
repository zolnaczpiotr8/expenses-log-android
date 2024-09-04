package zolnaczpiotr8.com.github.expenses.log.core.database.converters

import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ZonedDateTimeConverterTest {
    private val subjectUnderTest: ZonedDateTimeConverter = ZonedDateTimeConverter()

    @Test
    fun `given null when 'fromZonedDateTime' is called then null is returned`() {
        // GIVEN
        val zonedDateTime = null

        // WHEN
        val result = subjectUnderTest.fromZonedDateTime(zonedDateTime)

        // THEN
        assertNull(result)
    }

    @Test
    fun `given null when 'toZonedDateTime' is called then null is returned`() {
        // GIVEN
        val string: String? = null

        // WHEN
        val result = subjectUnderTest.toZonedDateTime(string)

        // THEN
        assertNull(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given empty string when 'toZonedDateTime' is called then exception is thrown`() {
        // GIVEN
        val string = ""

        // WHEN
        subjectUnderTest.toZonedDateTime(string)

        // THEN
        // exception is thrown
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given string in invalid format when 'toZonedDateTime' is called then exception is thrown`() {
        // GIVEN
        val string = "25-1994-"

        // WHEN
        subjectUnderTest.toZonedDateTime(string)

        // THEN
        // exception is thrown
    }

    @Test
    fun `given zoned date time when 'fromZonedDateTime' is called then formatted string is returned`() {
        // GIVEN
        val zonedDateTime =
            ZonedDateTime.of(
                1_994,
                12,
                25,
                14,
                30,
                0,
                0,
                ZoneId.of("GMT+2"),
            )

        // WHEN
        val result = subjectUnderTest.fromZonedDateTime(zonedDateTime)

        // THEN
        assertEquals(
            expected = "1994-12-25T14:30:00+02:00[GMT+02:00]",
            actual = result,
        )
    }

    @Test
    fun `given correct string when 'toZonedDateTime' is called then zoned date time is returned`() {
        // GIVEN
        val string = "2011-12-03T10:15:30+01:00[Europe/Paris]"

        // WHEN
        val result = subjectUnderTest.toZonedDateTime(string)

        // THEN
        val expected =
            ZonedDateTime.of(
                2_011,
                12,
                3,
                10,
                15,
                30,
                0,
                ZoneId.of("Europe/Paris"),
            )
        assertEquals(
            expected = expected,
            actual = result,
        )
    }
}
