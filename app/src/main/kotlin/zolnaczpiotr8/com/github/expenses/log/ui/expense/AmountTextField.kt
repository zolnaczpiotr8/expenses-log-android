package zolnaczpiotr8.com.github.expenses.log.ui.expense

import android.icu.util.Currency
import android.icu.util.ULocale
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.ClearIconButton
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldCharacterCounter
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldTrailingIconState

const val AMOUNT_CHARACTERS_LIMIT = 10

@Composable
fun AmountTextField(
    modifier: Modifier = Modifier,
    state: AmountTextFieldState = rememberAmountTextFieldState(),
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
) {
  val errorLabel: String? =
      when (state.error) {
        AmountError.None -> null
        AmountError.Empty -> stringResource(R.string.required_text_field_error_label)
        else -> stringResource(R.string.invalid_format_error)
      }
  OutlinedTextField(
      modifier = modifier.semantics { errorLabel?.let { error(it) } },
      state = state.textState,
      isError = state.error != AmountError.None,
      onKeyboardAction = { onImeAction() },
      inputTransformation = InputTransformation.maxLength(AMOUNT_CHARACTERS_LIMIT),
      lineLimits = TextFieldLineLimits.SingleLine,
      label = { Text(stringResource(R.string.amount_label)) },
      keyboardOptions =
          KeyboardOptions(
              keyboardType = KeyboardType.Decimal,
              imeAction = imeAction,
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
              state.textState.text.isEmpty() -> TextFieldTrailingIconState.None
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
                modifier = Modifier.semantics { hideFromAccessibility() },
                text = label,
            )
          }
              ?: TextFieldCharacterCounter(
                  count = state.textState.text.length,
                  limit = AMOUNT_CHARACTERS_LIMIT,
              )
        }
      },
  )
}

@Composable
fun rememberAmountTextFieldState(
    currencyCode: String = "",
    text: String = "",
    error: AmountError = AmountError.None,
): AmountTextFieldState {
  val errorState: MutableState<AmountError> =
      rememberSaveable(
          error,
          stateSaver = AmountError.Saver,
      ) {
        mutableStateOf(error)
      }
  val textState = rememberTextFieldState(text)
  val currency =
      remember(currencyCode) {
        if (currencyCode.isEmpty()) {
          Currency.getInstance(ULocale.getDefault())
        } else {
          Currency.getInstance(currencyCode)
        }
      }
  return remember(currency, error) {
    AmountTextFieldState(
        currency = currency,
        errorState = errorState,
        textState = textState,
    )
  }
}

class AmountTextFieldState(
    val currency: Currency,
    val textState: TextFieldState,
    private val errorState: MutableState<AmountError>,
) {

  val error: AmountError by errorState

  fun clear() {
    textState.clearText()
  }

  fun validate() {
    if (textState.text.isBlank()) {
      errorState.value = AmountError.Empty
      return
    }
    val double =
        try {
          textState.text.toString().toDouble()
        } catch (exception: NumberFormatException) {
          errorState.value = AmountError.Format
          return
        }
    if (double <= 0.0) {
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
    val Format = AmountError(2)

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
