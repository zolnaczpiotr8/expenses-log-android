package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TITLE_CHARACTERS_LIMIT
import zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields.TextFieldCharacterCounter

@Composable
fun CategoryComboBox(
    modifier: Modifier = Modifier,
    state: CategoryComboBoxState = rememberCategoryComboBoxState(),
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
  val errorLabel = stringResource(R.string.required_text_field_error_label)
  ExposedDropdownMenuBox(
      expanded = state.isExpanded,
      onExpandedChange = state::expand,
  ) {
    OutlinedTextField(
        modifier =
            modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable).semantics {
              if (state.isError) {
                error(errorLabel)
              }
            },
        isError = state.isError,
        label = { Text(stringResource(R.string.category_label)) },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = imeAction,
            ),
        onKeyboardAction = { onImeAction() },
        inputTransformation = InputTransformation.maxLength(TITLE_CHARACTERS_LIMIT),
        supportingText = {
          Crossfade(state.isError) {
            if (it) {
              Text(
                  modifier = Modifier.semantics { hideFromAccessibility() },
                  text = errorLabel,
              )
            } else {
              TextFieldCharacterCounter(
                  count = state.textFieldState.text.length,
                  limit = TITLE_CHARACTERS_LIMIT,
              )
            }
          }
        },
        trailingIcon = {
          val onClickLabel = stringResource(R.string.show_categories_action_label)
          ExposedDropdownMenuDefaults.TrailingIcon(
              expanded = state.isExpanded,
              modifier =
                  Modifier.menuAnchor(ExposedDropdownMenuAnchorType.SecondaryEditable).semantics {
                    onClick(
                        label = onClickLabel,
                        action = null,
                    )
                  },
          )
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        state = state.textFieldState,
        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
    )

    ExposedDropdownMenu(
        expanded = state.isExpanded,
        onDismissRequest = state::collapse,
    ) {
      val category = state.textFieldState.text.toString()
      state.titles
          .filter { it.contains(other = category, ignoreCase = true) }
          .forEach {
            key(it) {
              val onClickLabel = stringResource(R.string.choose_action_label)
              DropdownMenuItem(
                  modifier =
                      Modifier.semantics {
                        onClick(
                            label = onClickLabel,
                            action = null,
                        )
                      },
                  onClick = {
                    state.collapse()
                    state.textFieldState.setTextAndPlaceCursorAtEnd(it)
                  },
                  text = { Text(text = it) },
                  contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
              )
            }
          }
    }
  }
}

@Composable
fun rememberCategoryComboBoxState(
    category: String = "",
    titles: List<String> = emptyList(),
    isError: Boolean = false,
    isExpanded: Boolean = false,
): CategoryComboBoxState {
  val textFieldState = rememberTextFieldState(category)
  val expandedState = rememberSaveable(isExpanded) { mutableStateOf(isExpanded) }
  val errorState = rememberSaveable(isError) { mutableStateOf(isError) }
  return remember(titles, isError, isExpanded) {
    CategoryComboBoxState(
        titles = titles,
        expandedState = expandedState,
        textFieldState = textFieldState,
        errorState = errorState,
    )
  }
}

class CategoryComboBoxState(
    val titles: List<String>,
    val textFieldState: TextFieldState,
    private val errorState: MutableState<Boolean>,
    private val expandedState: MutableState<Boolean>,
) {
  val isError: Boolean by errorState
  val isExpanded: Boolean by expandedState

  fun collapse() {
    expand(false)
  }

  fun clear() {
    textFieldState.clearText()
  }

  fun expand(
      expand: Boolean,
  ) {
    expandedState.value = expand
  }

  fun validate() {
    errorState.value = textFieldState.text.isBlank()
  }
}
