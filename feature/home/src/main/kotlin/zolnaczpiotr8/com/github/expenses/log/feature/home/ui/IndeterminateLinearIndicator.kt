package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
internal fun IndeterminateLinearIndicator(
    label: String = "",
) {
    LinearProgressIndicator(
        Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = label
            },
    )
}
