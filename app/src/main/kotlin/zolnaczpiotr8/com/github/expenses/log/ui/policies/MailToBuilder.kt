package zolnaczpiotr8.com.github.expenses.log.ui.policies

import android.net.Uri

private const val MAIL_TO_SCHEME = "mailto"

object MailToBuilder {

  fun build(eMail: String): String =
      Uri.Builder().scheme(MAIL_TO_SCHEME).encodedOpaquePart(eMail).toString()
}
