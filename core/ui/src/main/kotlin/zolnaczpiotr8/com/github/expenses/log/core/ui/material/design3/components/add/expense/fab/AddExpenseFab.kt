package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.add.expense.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import zolnaczpiotr8.com.github.expenses.log.core.ui.R
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.theme.ExpensesLogTheme

@Composable
fun AddExpenseFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_expense_fab_description),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ExpensesLogTheme {
        AddExpenseFab(
            onClick = {
            },
        )
    }
}
