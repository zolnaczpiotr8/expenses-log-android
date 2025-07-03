package zolnaczpiotr8.com.github.expenses.log.database.daos

import android.icu.math.BigDecimal
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseWithCategoryEntity
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
interface ExpenseDao {

    @Query(
        """
        SELECT expense.title, expense.uuid, expense.amount, category.title AS category_title, expense.created
        FROM expense 
        JOIN category 
        on category.uuid = expense.category_uuid
        JOIN date_filter_time_stamp
        WHERE CAST(expense.created/1000 AS INT) BETWEEN 
            date_filter_time_stamp.start AND 
            date_filter_time_stamp.finish
        ORDER BY expense.created DESC    
    """,
    )
    fun expenses(): Flow<List<ExpenseWithCategoryEntity>>

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
    @Query(
        """
        INSERT INTO expense (title, amount, uuid, category_uuid, created)
        SELECT :title, :amount, :uuid, category.uuid, :created
        FROM category
        WHERE category.title = :category
    """,
    )
    suspend fun insert(
        title: String?,
        amount: BigDecimal,
        category: String,
        uuid: String = Uuid.random().toHexString(),
        created: Instant = Clock.System.now(),
    )

    @Query(
        """
            DELETE from expense WHERE uuid = :uuid
        """,
    )
    suspend fun delete(
        uuid: String,
    )
}
