package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.ClearIconButton

@Composable
fun TitleTextField(
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    state: TitleTextFieldState = rememberTitleTextFieldState(),
    onImeAction: () -> Unit = {},
) {
  val errorLabel = stringResource(R.string.required_text_field_error_label)
  OutlinedTextField(
      modifier =
          modifier.semantics {
            if (state.isError) {
              error(errorLabel)
            }
          },
      isError = state.isError,
      lineLimits = TextFieldLineLimits.SingleLine,
      label = {
        Text(
            text = stringResource(R.string.title_label),
        )
      },
      inputTransformation = InputTransformation.maxLength(TITLE_CHARACTERS_LIMIT),
      keyboardOptions =
          KeyboardOptions(
              keyboardType = KeyboardType.Text,
              imeAction = imeAction,
          ),
      onKeyboardAction = { onImeAction() },
      trailingIcon = {
        Crossfade(
            when {
              state.isError -> TextFieldTrailingIconState.Error
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
      state = state.textState,
      supportingText = {
        Crossfade(state.isError) {
          if (it) {
            Text(
                modifier = Modifier.semantics { hideFromAccessibility() },
                text = errorLabel,
            )
          } else {
            TextFieldCharacterCounter(
                count = state.textState.text.length,
                limit = TITLE_CHARACTERS_LIMIT,
            )
          }
        }
      },
  )
}

@Composable
fun rememberTitleTextFieldState(
    isError: Boolean = false,
    title: String = "",
): TitleTextFieldState {
  val textState = rememberTextFieldState(title)
  val errorState = rememberSaveable(isError) { mutableStateOf(isError) }
  return remember(isError) {
    TitleTextFieldState(
        textState = textState,
        errorState = errorState,
    )
  }
}

class TitleTextFieldState(
    val textState: TextFieldState,
    private val errorState: MutableState<Boolean>,
) {
  val isError: Boolean by errorState

  fun validate() {
    errorState.value = textState.text.isBlank()
  }

  fun clear() {
    textState.clearText()
  }
}
