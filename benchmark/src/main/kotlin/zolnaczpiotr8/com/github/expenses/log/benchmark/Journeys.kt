package zolnaczpiotr8.com.github.expenses.log.benchmark

import androidx.test.uiautomator.UiDevice

fun UiDevice.goToNewExpense() {
  clickMainMenu()
  clickNewExpense()
}

fun UiDevice.addNewExpense() {
  enterTitle(randomExpenseTitle())
  enterAmount(randomAmount())
  enterCategory(randomCategoryTitle())
  clickSave()
}

fun UiDevice.goToNewCategory() {
  clickMainMenu()
  clickNewCategory()
}

fun UiDevice.addNewCategory() {
  enterTitle(randomCategoryTitle())
  clickSave()
}
