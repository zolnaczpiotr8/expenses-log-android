package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.error
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
    onImeAction: () -> Unit = {
    },
) {
    val errorLabel = stringResource(R.string.required_text_field_error_label)
    OutlinedTextField(
        modifier = modifier.semantics {
            if (state.isError) {
                error(errorLabel)
            }
        },
        singleLine = true,
        isError = state.isError,
        label = {
            Text(
                text = stringResource(R.string.title_label),
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onAny = {
                onImeAction()
            },
        ),
        trailingIcon = {
            Crossfade(
                when {
                    state.isError -> TextFieldTrailingIconState.Error
                    state.text.isEmpty() -> TextFieldTrailingIconState.None
                    else -> TextFieldTrailingIconState.Clear
                },
            ) {
                when (it) {
                    TextFieldTrailingIconState.Error -> Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                    )
                    TextFieldTrailingIconState.Clear -> ClearIconButton(state::clear)
                    else -> Unit
                }
            }
        },
        value = state.text,
        onValueChange = {
            state.onTextChange(
                it.take(TITLE_CHARACTERS_LIMIT),
            )
        },
        supportingText = {
            Crossfade(state.isError) {
                if (it) {
                    Text(
                        modifier = Modifier
                            .clearAndSetSemantics {
                            },
                        text = errorLabel,
                    )
                } else {
                    TextFieldCharacterCounter(
                        count = state.text.length,
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
    val textState = rememberSaveable(title) {
        mutableStateOf(title)
    }
    val errorState = rememberSaveable(isError) {
        mutableStateOf(isError)
    }
    return remember(isError, title) {
        TitleTextFieldState(
            textState = textState,
            errorState = errorState,
        )
    }
}

class TitleTextFieldState(
    private val textState: MutableState<String>,
    private val errorState: MutableState<Boolean>,
) {
    val text: String by textState
    val isError: Boolean by errorState

    fun validate() {
        errorState.value = text.isBlank()
    }

    fun clear() {
        onTextChange("")
    }

    fun onTextChange(
        text: String,
    ) {
        textState.value = text
        errorState.value = false
    }
}
