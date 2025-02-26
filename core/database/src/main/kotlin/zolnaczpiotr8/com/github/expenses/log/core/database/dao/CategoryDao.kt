package zolnaczpiotr8.com.github.expenses.log.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.core.database.model.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface CategoryDao {

    @Query(
        """
        SELECT *
        FROM category, expense
        WHERE expense.category_uuid = category.uuid
    """,
    )
    fun categories(): Flow<Map<CategoryEntity, List<ExpenseEntity>>>

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
