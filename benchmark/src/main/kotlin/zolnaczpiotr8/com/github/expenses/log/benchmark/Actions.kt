package zolnaczpiotr8.com.github.expenses.log.benchmark

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

fun UiDevice.agreeToTermsIfNeeded() {
  findObject(By.text("I Agree"))?.click()
}

fun UiDevice.clickMainMenu() {
  val label = "Menu"
  findObjects(By.desc(label))
      .reduce { menu, menu1 ->
        if (menu.visibleCenter.y > menu.visibleCenter.y) {
          menu
        } else {
          menu1
        }
      }
      .click()
  wait(Until.findObject(By.text(label)), Config.WAITING_TIMEOUT)
}

fun UiDevice.clickNewExpense() {
  findObject(By.text("New expense")).click()
}

fun UiDevice.clickShowExpenses() {
  findObject(By.text("Show expenses")).click()
}

fun UiDevice.clickNewCategory() {
  findObject(By.text("New category")).click()
}

fun UiDevice.enterTitle(title: String) {
  findObject(By.clazz(EditText::class.java).hasChild(By.text("Title"))).text = title
}

fun UiDevice.enterAmount(amount: Number) {
  findObject(By.clazz(EditText::class.java).hasChild(By.text("Amount"))).text = amount.toString()
}

fun UiDevice.enterCategory(category: String) {
  findObject(By.clazz(EditText::class.java).hasChild(By.text("Category"))).text = category
}

fun UiDevice.clickSave() {
  findObjects(By.clazz(Button::class.java))
      .reduce { button, button2 ->
        if (button.visibleCenter.y > button2.visibleCenter.y) {
          button
        } else {
          button2
        }
      }
      .click()
}

fun UiDevice.clickGoBack() {
  findObjects(By.clazz(Button::class.java))
      .reduce { button, button2 ->
        if (button.visibleCenter.y < button2.visibleCenter.y) {
          button
        } else {
          button2
        }
      }
      .click()
}

fun UiDevice.waitForHome() {
  wait(Until.hasObject(By.text("Expenses Log")), Config.WAITING_TIMEOUT)
}

fun UiDevice.waitForExpensesCategories() {
  wait(Until.hasObject(By.text("Expenses categories")), Config.WAITING_TIMEOUT)
}

fun UiDevice.scrollExpensesDown() {
  findExpenses().scrollDown()
}

fun UiDevice.scrollExpensesUp() {
  findExpenses().scrollUp()
}

private fun UiDevice.findExpenses(): UiObject2 =
    findObjects(By.scrollable(true)).reduce { scrollable, scrollable1 ->
      if (scrollable.visibleCenter.y < scrollable1.visibleCenter.y) {
        scrollable
      } else {
        scrollable1
      }
    }

fun UiDevice.scrollCategoriesDown() {
  findCategories().scrollDown()
}

fun UiDevice.scrollCategoriesUp() {
  findCategories().scrollUp()
}

private fun UiDevice.findCategories(): UiObject2 = findObject(By.scrollable(true))

private fun UiObject2.scrollDown() {
  scroll(Direction.DOWN, Config.SCROLL_PERCENTAGE)
}

private fun UiObject2.scrollUp() {
  scroll(Direction.UP, Config.SCROLL_PERCENTAGE)
}

fun UiDevice.clickShowEmptyCategoriesIfNotChecked() {
  val filter = findObject(By.checkable(true).hasChild(By.clazz(CheckBox::class.java)))
  if (filter.isChecked) {
    return
  }
  filter.click()
}
