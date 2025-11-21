package zolnaczpiotr8.com.github.expenses.log.database.entities.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.UUID
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity

private const val CATEGORY_UUID_COLUMN_NAME = "category_uuid"

@Entity(
    tableName = "expense",
    foreignKeys =
        [
            ForeignKey(
                entity = CategoryEntity::class,
                parentColumns = ["uuid"],
                childColumns = [CATEGORY_UUID_COLUMN_NAME],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
            ),
        ],
    indices =
        [
            Index(
                CATEGORY_UUID_COLUMN_NAME,
            ),
        ],
)
data class ExpenseEntity
constructor(
    @PrimaryKey val uuid: UUID,
    val title: String?,
    val amount: Double,
    @ColumnInfo(
        name = CATEGORY_UUID_COLUMN_NAME,
    )
    val categoryUuid: String,
    val created: Instant,
)
