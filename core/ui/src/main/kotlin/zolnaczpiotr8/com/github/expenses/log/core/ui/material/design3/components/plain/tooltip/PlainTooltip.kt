package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.plain.tooltip

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PlainTooltip(
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
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(supportingText)
            }
        },
        state = state,
        focusable = false,
        content = content,
    )
}
