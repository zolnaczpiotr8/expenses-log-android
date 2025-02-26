package zolnaczpiotr8.com.github.expenses.log.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseCategoryTitle
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface ExpenseDao {

    @Query(
        """
        SELECT expense.uuid AS uuid,
               expense.title AS title,
               expense.amount AS amount,
               category.title AS categoryTitle
        FROM expense,
             category ON expense.category_uuid = category.uuid
    """,
    )
    fun expenses(): Flow<List<ExpenseCategoryTitle>>

    @OptIn(ExperimentalUuidApi::class)
    @Query(
        """
            DELETE from expense WHERE uuid = :uuid
        """,
    )
    suspend fun delete(
        uuid: Uuid,
    )
}
