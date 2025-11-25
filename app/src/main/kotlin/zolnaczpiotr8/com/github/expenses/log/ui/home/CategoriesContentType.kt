package zolnaczpiotr8.com.github.expenses.log.ui.home

@JvmInline
value class CategoriesContentType(val value: Int) {

  companion object {
    val Category = CategoriesContentType(0)
    val Text = CategoriesContentType(1)
    val Spacer = CategoriesContentType(2)
  }
}
