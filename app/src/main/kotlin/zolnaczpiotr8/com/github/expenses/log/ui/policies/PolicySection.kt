package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings

@Composable
fun PolicySection(title: String, body: String, footer: String? = null) {
  PolicySection(
      title = title,
      body = body,
      footer = footer?.run(::AnnotatedString),
  )
}

@Composable
fun PolicySection(title: String, body: String, footer: AnnotatedString?) {
  Column(verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1)) {
    Text(
        modifier = Modifier.semantics { heading() },
        text = title,
        style = MaterialTheme.typography.titleMedium,
    )
    Text(text = body, style = MaterialTheme.typography.bodyMedium)
    footer?.let { Text(text = footer, style = MaterialTheme.typography.bodyMedium) }
  }
}
