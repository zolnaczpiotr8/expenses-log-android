package zolnaczpiotr8.com.github.expenses.log.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(
    tableName = "expenses",
)
internal data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Long,
    val created: ZonedDateTime,
)
