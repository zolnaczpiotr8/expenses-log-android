package zolnaczpiotr8.com.github.expenses.log.core.model

import java.time.ZonedDateTime

data class Expense(
    val id: Int,
    val amount: Long,
    val description: String,
    val created: ZonedDateTime,
)
