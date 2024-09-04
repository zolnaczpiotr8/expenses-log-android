package zolnaczpiotr8.com.github.expenses.log.feature.add.expense.save.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme
import zolnaczpiotr8.com.github.expenses.log.feature.add.expense.R

@Composable
internal fun SaveExtendedFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(R.string.save_fab_description),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        SaveExtendedFab(
            onClick = {},
        )
    }
}
