package zolnaczpiotr8.com.github.expenses.log.feature.expense.ui

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.buttons.icon.buttons.ClearIconButton
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TRAILING_ICON_CLEAR
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TRAILING_ICON_ERROR
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TRAILING_ICON_NONE
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.text.fields.TextFieldCharacterCounter
import zolnaczpiotr8.com.github.expenses.log.feature.expense.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

private const val AMOUNT_CHARACTERS_LIMIT = 10
private const val EMPTY_ERROR = 1
private const val INVALID_FORMAT_ERROR = 2

@Composable
fun AmountTextField(
    modifier: Modifier = Modifier,
    state: AmountTextFieldState,
) {
    val errorLabel: String? = when (state.error) {
        null -> null
        EMPTY_ERROR -> stringResource(coreUiR.string.required_text_field_error_label)
        else -> stringResource(R.string.invalid_format_error)
    }
    OutlinedTextField(
        modifier = modifier.semantics {
            errorLabel?.let {
                error(it)
            }
        },
        value = state.text,
        isError = state.error != null,
        singleLine = true,
        label = {
            Text(stringResource(R.string.amount_label))
        },
        onValueChange = {
            state.onTextChange(it.take(AMOUNT_CHARACTERS_LIMIT))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next,
        ),
        prefix = {
            Text(
                modifier = Modifier.clearAndSetSemantics {
                    contentDescription = state.currency.displayName
                },
                text = state.currency.symbol,
            )
        },
        trailingIcon = {
            Crossfade(
                when {
                    state.error != null -> TRAILING_ICON_ERROR
                    state.text.isEmpty() -> TRAILING_ICON_NONE
                    else -> TRAILING_ICON_CLEAR
                },
            ) {
                when (it) {
                    TRAILING_ICON_ERROR -> Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                    )
                    TRAILING_ICON_CLEAR -> ClearIconButton(state::clear)
                    else -> Unit
                }
            }
        },
        supportingText = {
            Crossfade(errorLabel) {
                it?.let { label ->
                    Text(
                        modifier = Modifier
                            .clearAndSetSemantics {
                            },
                        text = label,
                    )
                } ?: TextFieldCharacterCounter(
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
    val errorState: MutableState<Int?> = rememberSaveable {
        mutableStateOf(null)
    }
    val textState = rememberSaveable {
        mutableStateOf("")
    }
    val currency = remember(currencyCode) {
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
    private val errorState: MutableState<Int?>,
) {

    val text: String by textState
    val error: Int? by errorState

    fun clear() {
        onTextChange("")
    }

    fun onTextChange(
        text: String,
    ) {
        textState.value = text
        errorState.value = null
    }

    fun validate() {
        if (text.isBlank()) {
            errorState.value = EMPTY_ERROR
            return
        }
        val bigDecimal = try {
            BigDecimal(text)
        } catch (exception: NumberFormatException) {
            errorState.value = INVALID_FORMAT_ERROR
            return
        }
        if (bigDecimal <= BigDecimal.ZERO) {
            errorState.value = INVALID_FORMAT_ERROR
            return
        }
        errorState.value = null
    }
}
