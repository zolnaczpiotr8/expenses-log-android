package zolnaczpiotr8.com.github.expenses.log.ui.expense

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.GoBackIconButton

@Composable
fun NewExpenseTopBar(onGoBackClick: () -> Unit, scrollBehavior: TopAppBarScrollBehavior? = null) {
  TopAppBar(
      title = { Text(stringResource(R.string.new_expense)) },
      navigationIcon = {
        GoBackIconButton(
            onClick = onGoBackClick,
        )
      },
      scrollBehavior = scrollBehavior,
  )
}
