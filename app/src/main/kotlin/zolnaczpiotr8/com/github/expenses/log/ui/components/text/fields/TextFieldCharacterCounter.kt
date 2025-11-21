package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import zolnaczpiotr8.com.github.expenses.log.R

const val TITLE_CHARACTERS_LIMIT = 30

@Composable
fun TextFieldCharacterCounter(
    modifier: Modifier = Modifier,
    count: Int,
    limit: Int,
) {
  val label =
      stringResource(
          R.string.character_count_and_limit_label,
          count,
          limit,
      )

  Row(
      modifier =
          modifier.fillMaxWidth().semantics(mergeDescendants = true) { contentDescription = label },
      horizontalArrangement = Arrangement.End,
  ) {
    Text(
        modifier = Modifier.semantics { hideFromAccessibility() },
        text =
            stringResource(
                R.string.character_counter_text,
                count,
                limit,
            ),
    )
  }
}
