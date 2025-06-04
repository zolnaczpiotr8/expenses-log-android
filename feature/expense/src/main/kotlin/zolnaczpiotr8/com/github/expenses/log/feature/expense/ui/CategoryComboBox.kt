package zolnaczpiotr8.com.github.expenses.log.feature.expense.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
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
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.collections.immutable.ImmutableList
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TITLE_CHARACTERS_LIMIT
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TextFieldCharacterCounter
import zolnaczpiotr8.com.github.expenses.log.feature.expense.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryComboBox(
    modifier: Modifier = Modifier,
    state: CategoryComboBoxState,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {
    },
) {
    val errorLabel = stringResource(coreUiR.string.required_text_field_error_label)
    ExposedDropdownMenuBox(
        expanded = state.isExpanded,
        onExpandedChange = state::expand,
    ) {
        OutlinedTextField(
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .semantics {
                    if (state.isError) {
                        error(errorLabel)
                    }
                },
            isError = state.isError,
            label = {
                Text(stringResource(R.string.category_label))
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
                            count = state.textFieldValue.text.length,
                            limit = TITLE_CHARACTERS_LIMIT,
                        )
                    }
                }
            },
            onValueChange = {
                state.onTextChange(
                    it.copy(
                        text = it.text.take(TITLE_CHARACTERS_LIMIT),
                    ),
                )
            },
            trailingIcon = {
                val onClickLabel = stringResource(R.string.show_categories_action_label)
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = state.isExpanded,
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.SecondaryEditable)
                        .semantics {
                            onClick(
                                label = onClickLabel,
                                action = null,
                            )
                        },
                )
            },
            singleLine = true,
            value = state.textFieldValue,
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = state.isExpanded,
            onDismissRequest = state::collapse,
        ) {
            state.titles
                .filter {
                    it.contains(
                        other = state.textFieldValue.text,
                        ignoreCase = true,
                    )
                }.forEach {
                    key(it) {
                        val onClickLabel = stringResource(coreUiR.string.choose_action_label)
                        DropdownMenuItem(
                            modifier = Modifier.semantics {
                                onClick(
                                    label = onClickLabel,
                                    action = null,
                                )
                            },
                            onClick = {
                                if (
                                    it.equals(
                                        other = state.textFieldValue.text,
                                        ignoreCase = true,
                                    ).not()
                                ) {
                                    state.onTextChange(
                                        TextFieldValue(
                                            text = it,
                                            selection = TextRange(it.length),
                                        ),
                                    )
                                    state.collapse()
                                }
                            },
                            text = {
                                Text(text = it)
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
        }
    }
}

@Composable
fun rememberCategoryComboBoxState(
    category: String,
    titles: ImmutableList<String>,
): CategoryComboBoxState {
    val textFieldValueState = rememberSaveable(
        category,
        stateSaver = TextFieldValue.Saver,
    ) {
        mutableStateOf(
            TextFieldValue(
                text = category,
                selection = TextRange(category.length),
            ),
        )
    }
    val expandedState = rememberSaveable {
        mutableStateOf(false)
    }
    val errorState = rememberSaveable {
        mutableStateOf(false)
    }
    return remember(titles) {
        CategoryComboBoxState(
            titles = titles,
            expandedState = expandedState,
            textFieldValueState = textFieldValueState,
            errorState = errorState,
        )
    }
}

class CategoryComboBoxState(
    val titles: ImmutableList<String>,
    private val textFieldValueState: MutableState<TextFieldValue>,
    private val errorState: MutableState<Boolean>,
    private val expandedState: MutableState<Boolean>,
) {
    val textFieldValue: TextFieldValue by textFieldValueState
    val isError: Boolean by errorState
    val isExpanded: Boolean by expandedState

    fun collapse() {
        expand(false)
    }

    fun expand(
        expand: Boolean,
    ) {
        expandedState.value = expand
        errorState.value = false
    }

    fun validate() {
        errorState.value = textFieldValue.text.isBlank()
    }

    fun onTextChange(
        textFieldValue: TextFieldValue,
    ) {
        textFieldValueState.value = textFieldValue
        errorState.value = false
    }
}
