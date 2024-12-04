package zolnaczpiotr8.com.github.expenses.log.feature.expense.model

sealed interface NewExpenseUiModel {

    data object Empty : NewExpenseUiModel

    data object Success : NewExpenseUiModel
}
