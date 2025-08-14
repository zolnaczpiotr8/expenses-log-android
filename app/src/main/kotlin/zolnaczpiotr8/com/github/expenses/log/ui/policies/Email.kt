package zolnaczpiotr8.com.github.expenses.log.ui.policies

import android.net.Uri
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink

private const val E_MAIL = "simkovadagmar80@gmail.com"
private val mailTo: String by lazy {
  Uri.Builder().scheme("mailto").encodedOpaquePart(E_MAIL).toString()
}

@Composable
@ReadOnlyComposable
fun eMail(): AnnotatedString = buildAnnotatedString {
  withLink(
      LinkAnnotation.Url(
          url = mailTo,
          styles =
              TextLinkStyles(
                  MaterialTheme.typography.bodyMedium
                      .toSpanStyle()
                      .copy(
                          textDecoration = TextDecoration.Underline,
                          color = MaterialTheme.colorScheme.tertiary)),
      )) {
        append(E_MAIL)
      }
}
