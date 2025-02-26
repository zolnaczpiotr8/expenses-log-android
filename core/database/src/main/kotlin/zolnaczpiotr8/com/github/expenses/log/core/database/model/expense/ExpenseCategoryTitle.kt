package zolnaczpiotr8.com.github.expenses.log.core.database.model.expense

import android.icu.math.BigDecimal
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ExpenseCategoryTitle
@OptIn(ExperimentalUuidApi::class)
constructor(
    val uuid: Uuid,
    val title: String?,
    val amount: BigDecimal,
    val categoryTitle: String,
)
