package zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.fab

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zolnaczpiotr8.com.github.expenses.log.ui.components.Measurements

@Composable
fun SmallFabSpacer() {
    Spacer(
        Modifier.height(
            Measurements.SmallFab.height +
                Measurements.SmallFab.padding,
        ),
    )
}
