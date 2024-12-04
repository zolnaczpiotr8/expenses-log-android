package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlainTooltip(
    modifier: Modifier = Modifier,
    supportingText: String,
    interactionSource: InteractionSource,
    content: @Composable () -> Unit,
) {
    val state = rememberTooltipState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    LaunchedEffect(isFocused) {
        if (isFocused) {
            state.show()
        } else {
            state.dismiss()
        }
    }
    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        state = state,
        focusable = false,
        tooltip = {
            Text(
                text = supportingText,
            )
        },
        content = content,
    )
}
