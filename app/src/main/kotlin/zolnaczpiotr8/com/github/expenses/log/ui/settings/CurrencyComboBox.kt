package zolnaczpiotr8.com.github.expenses.log.ui.settings

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.allCaps
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldCharacterCounter

private const val CHARACTERS_LIMIT = 3

@Composable
fun CurrencyComboBox(
    modifier: Modifier = Modifier,
    currentCurrency: String,
    onCurrencyClick: (String) -> Unit = {},
    availableCurrencies: List<String>,
) {
  val expandedState = rememberSaveable { mutableStateOf(false) }
  val currencyState = rememberTextFieldState()
  LaunchedEffect(currentCurrency) { currencyState.setTextAndPlaceCursorAtEnd(currentCurrency) }
  ExposedDropdownMenuBox(
      expanded = expandedState.value,
      onExpandedChange = { expandedState.value = it },
  ) {
    OutlinedTextField(
        modifier = modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
        supportingText = {
          TextFieldCharacterCounter(
              count = currencyState.text.length,
              limit = CHARACTERS_LIMIT,
          )
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.None,
            ),
        lineLimits = TextFieldLineLimits.SingleLine,
        inputTransformation =
            InputTransformation.maxLength(CHARACTERS_LIMIT)
                .then(InputTransformation.allCaps(Locale.current)),
        trailingIcon = {
          val onClickLabel = stringResource(R.string.show_available_currencies_action_label)
          ExposedDropdownMenuDefaults.TrailingIcon(
              expanded = expandedState.value,
              modifier =
                  Modifier.menuAnchor(ExposedDropdownMenuAnchorType.SecondaryEditable).semantics {
                    onClick(
                        label = onClickLabel,
                        action = null,
                    )
                  },
          )
        },
        state = currencyState,
        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
    )

    ExposedDropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false },
    ) {
      availableCurrencies
          .filter { it.contains(currencyState.text) }
          .forEach {
            key(it) {
              val currencyCode = stringResource(R.string.currency_code_item_label, it)
              val onClickLabel = stringResource(R.string.choose_action_label)
              DropdownMenuItem(
                  modifier =
                      Modifier.semantics {
                        onClick(
                            label = onClickLabel,
                            action = null,
                        )
                      },
                  text = {
                    Text(
                        text = it,
                        modifier =
                            Modifier.clearAndSetSemantics { contentDescription = currencyCode },
                    )
                  },
                  onClick = {
                    expandedState.value = false
                    it.takeUnless { it == currentCurrency }?.let(onCurrencyClick)
                  },
                  contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
              )
            }
          }
    }
  }
}
