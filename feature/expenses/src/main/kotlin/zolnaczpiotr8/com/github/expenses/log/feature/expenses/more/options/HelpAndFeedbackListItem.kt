package zolnaczpiotr8.com.github.expenses.log.feature.expenses.more.options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options.OptionListItem
import zolnaczpiotr8.com.github.expenses.log.feature.expenses.R

@Composable
internal fun HelpAndFeedbackListItem(onClick: () -> Unit) {
    val onClickLabel = stringResource(R.string.navigate_on_click_label)
    OptionListItem(
        modifier =
        Modifier.semantics {
            onClick(
                label = onClickLabel,
                action = null,
            )
        },
        onClick = onClick,
        headline = stringResource(R.string.help_and_feedback_option_description),
        leadingIcon = Icons.Default.Info,
    )
}
