package zolnaczpiotr8.com.github.expenses.log.ui.expense

import android.icu.math.BigDecimal
import android.icu.util.Currency
import android.icu.util.ULocale
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.ClearIconButton
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldCharacterCounter
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldTrailingIconState

private const val AMOUNT_CHARACTERS_LIMIT = 10

@Composable
fun AmountTextField(
    modifier: Modifier = Modifier,
    state: AmountTextFieldState,
) {
  val errorLabel: String? =
      when (state.error) {
        AmountError.None -> null
        AmountError.Empty -> stringResource(R.string.required_text_field_error_label)
        else -> stringResource(R.string.invalid_format_error)
      }
  OutlinedTextField(
      modifier = modifier.semantics { errorLabel?.let { error(it) } },
      value = state.text,
      isError = state.error != AmountError.None,
      singleLine = true,
      label = { Text(stringResource(R.string.amount_label)) },
      onValueChange = { state.onTextChange(it.take(AMOUNT_CHARACTERS_LIMIT)) },
      keyboardOptions =
          KeyboardOptions(
              keyboardType = KeyboardType.Decimal,
              imeAction = ImeAction.Next,
          ),
      prefix = {
        Text(
            modifier =
                Modifier.clearAndSetSemantics { contentDescription = state.currency.displayName },
            text = state.currency.symbol,
        )
      },
      trailingIcon = {
        Crossfade(
            when {
              state.error != AmountError.None -> TextFieldTrailingIconState.Error
              state.text.isEmpty() -> TextFieldTrailingIconState.None
              else -> TextFieldTrailingIconState.Clear
            },
        ) {
          when (it) {
            TextFieldTrailingIconState.Error ->
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                )
            TextFieldTrailingIconState.Clear -> ClearIconButton(state::clear)
            else -> Unit
          }
        }
      },
      supportingText = {
        Crossfade(errorLabel) {
          it?.let { label ->
            Text(
                modifier = Modifier.clearAndSetSemantics {},
                text = label,
            )
          }
              ?: TextFieldCharacterCounter(
                  count = state.text.length,
                  limit = AMOUNT_CHARACTERS_LIMIT,
              )
        }
      },
  )
}

@Composable
fun rememberAmountTextFieldState(
    currencyCode: String,
): AmountTextFieldState {
  val errorState: MutableState<AmountError> =
      rememberSaveable(
          stateSaver = AmountError.Saver,
      ) {
        mutableStateOf(AmountError.None)
      }
  val textState = rememberSaveable { mutableStateOf("") }
  val currency =
      remember(currencyCode) {
        if (currencyCode.isEmpty()) {
          Currency.getInstance(ULocale.getDefault())
        } else {
          Currency.getInstance(currencyCode)
        }
      }
  return remember(currency) {
    AmountTextFieldState(
        currency = currency,
        errorState = errorState,
        textState = textState,
    )
  }
}

class AmountTextFieldState(
    val currency: Currency,
    private val textState: MutableState<String>,
    private val errorState: MutableState<AmountError>,
) {

  val text: String by textState
  val error: AmountError by errorState

  fun clear() {
    onTextChange("")
  }

  fun onTextChange(
      text: String,
  ) {
    textState.value = text
    errorState.value = AmountError.None
  }

  fun validate() {
    if (text.isBlank()) {
      errorState.value = AmountError.Empty
      return
    }
    val bigDecimal =
        try {
          BigDecimal(text)
        } catch (exception: NumberFormatException) {
          errorState.value = AmountError.Format
          return
        }
    if (bigDecimal <= BigDecimal.ZERO) {
      errorState.value = AmountError.Format
      return
    }
    errorState.value = AmountError.None
  }
}

@JvmInline
value class AmountError(val value: Int) {

  companion object {

    val None = AmountError(0)
    val Empty = AmountError(1)
    val Format = AmountError(1)

    val Saver =
        Saver<AmountError, Int>(
            save = { it.value },
            restore = {
              when (it) {
                Empty.value -> Empty
                Format.value -> Format
                else -> None
              }
            },
        )
  }
}
