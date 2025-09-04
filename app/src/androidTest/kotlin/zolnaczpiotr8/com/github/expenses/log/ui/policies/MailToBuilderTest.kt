package zolnaczpiotr8.com.github.expenses.log.ui.policies

import org.junit.Test

class MailToBuilderTest {

  @Test
  fun test() {
    val eMail = "email@email.com"
    assert(MailToBuilder.build(eMail) == "mailto:$eMail")
  }

  @Test
  fun empty() {
    assert(MailToBuilder.build("") == "mailto:")
  }
}
