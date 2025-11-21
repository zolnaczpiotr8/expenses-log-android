package zolnaczpiotr8.com.github.expenses.log.datastore

import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.model.Settings

class SettingsMapperTest {

  private val sut = SettingsMapper()

  @Test
  fun test() {
    val actual =
        sut.map(
            SettingsProto.getDefaultInstance()
                .newBuilderForType()
                .setCurrencyCode("USD")
                .setAgreedToTerms(true)
                .build()
        )

    val expected = Settings(currencyCode = "USD", agreedToTerms = true)

    assert(actual == expected)
  }
}
