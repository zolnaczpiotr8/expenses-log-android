package zolnaczpiotr8.com.github.expenses.log.ui.home

@JvmInline
value class ExpensesContentType(val value: Int) {

  companion object {
    val Expense = CategoriesContentType(0)
    val Header = CategoriesContentType(1)
  }
}
