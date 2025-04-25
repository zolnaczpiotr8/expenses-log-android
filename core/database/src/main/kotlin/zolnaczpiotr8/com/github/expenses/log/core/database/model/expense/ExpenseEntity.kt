package zolnaczpiotr8.com.github.expenses.log.core.database.model.expense

import android.icu.math.BigDecimal
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import zolnaczpiotr8.com.github.expenses.log.core.database.model.category.CategoryEntity

private const val CATEGORY_UUID_COLUMN_NAME = "category_uuid"

@Entity(
    tableName = "expense",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["uuid"],
            childColumns = [CATEGORY_UUID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(
            CATEGORY_UUID_COLUMN_NAME,
        ),
    ],
)
data class ExpenseEntity(
    @PrimaryKey
    val uuid: String,
    val title: String?,
    val amount: BigDecimal,
    @ColumnInfo(
        name = CATEGORY_UUID_COLUMN_NAME,
    )
    val categoryUuid: String,
    val created: Instant,
)
