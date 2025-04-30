package zolnaczpiotr8.com.github.expenses.log.feature.settings.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.collections.immutable.ImmutableList
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TextFieldCharacterCounter
import zolnaczpiotr8.com.github.expenses.log.feature.settings.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

private const val CHARACTERS_LIMIT = 3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyComboBox(
    modifier: Modifier = Modifier,
    currentCurrency: String,
    onCurrencyClick: (String) -> Unit = {
    },
    availableCurrencies: ImmutableList<String>,
) {
    val expandedState = rememberSaveable {
        mutableStateOf(false)
    }
    val currencyState = rememberSaveable(
        currentCurrency,
        stateSaver = TextFieldValue.Saver,
    ) {
        mutableStateOf(
            TextFieldValue(
                text = currentCurrency,
                selection = TextRange(currentCurrency.length),
            ),
        )
    }
    ExposedDropdownMenuBox(
        expanded = expandedState.value,
        onExpandedChange = {
            expandedState.value = it
        },
    ) {
        OutlinedTextField(
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            supportingText = {
                TextFieldCharacterCounter(
                    count = currencyState.value.text.length,
                    limit = CHARACTERS_LIMIT,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.None,
            ),
            singleLine = true,
            label = {
                val label = stringResource(R.string.currency_code_label)
                Text(
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription = label
                    },
                    text = stringResource(R.string.currency_label),
                )
            },
            trailingIcon = {
                val onClickLabel = stringResource(R.string.show_available_currencies_action_label)
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expandedState.value,
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
            value = currencyState.value,
            onValueChange = {
                currencyState.value = it.copy(
                    text = it.text.take(CHARACTERS_LIMIT),
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = expandedState.value,
            onDismissRequest = {
                expandedState.value = false
            },
        ) {
            availableCurrencies
                .filter {
                    it.contains(
                        other = currencyState.value.text,
                        ignoreCase = true,
                    )
                }
                .forEach {
                    key(it) {
                        val currencyCode = stringResource(R.string.currency_code_item_label, it)
                        val onClickLabel = stringResource(coreUiR.string.choose_action_label)
                        DropdownMenuItem(
                            modifier = Modifier.semantics {
                                onClick(
                                    label = onClickLabel,
                                    action = null,
                                )
                            },
                            text = {
                                Text(
                                    text = it,
                                    modifier = Modifier.clearAndSetSemantics {
                                        contentDescription = currencyCode
                                    },
                                )
                            },
                            onClick = {
                                if (
                                    it.equals(
                                        other = currentCurrency,
                                        ignoreCase = true,
                                    ).not()
                                ) {
                                    onCurrencyClick(it)
                                    expandedState.value = false
                                }
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
        }
    }
}
