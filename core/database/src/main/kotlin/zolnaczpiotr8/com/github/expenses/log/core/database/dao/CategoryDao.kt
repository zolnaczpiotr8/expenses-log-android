package zolnaczpiotr8.com.github.expenses.log.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import zolnaczpiotr8.com.github.expenses.log.core.database.model.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface CategoryDao {

    @Query(
        """
        SELECT *
        FROM category 
        LEFT JOIN expense
        ON expense.category_uuid = category.uuid 
        WHERE (expense.created >= :start) 
            AND (expense.created <= :end)
    """,
    )
    fun categories(
        start: Instant,
        end: Instant,
    ): Flow<Map<CategoryEntity, List<ExpenseEntity>>>

    @OptIn(ExperimentalUuidApi::class)
    @Transaction
    @Query(
        """
            DELETE from category WHERE uuid = :uuid
        """,
    )
    suspend fun delete(
        uuid: Uuid,
    )
}
