package zolnaczpiotr8.com.github.expenses.log.ui.components.text.fields

@JvmInline
value class TextFieldTrailingIconState(val value: Int) {
  companion object {
    val None = TextFieldTrailingIconState(0)
    val Error = TextFieldTrailingIconState(1)
    val Clear = TextFieldTrailingIconState(2)
  }
}
