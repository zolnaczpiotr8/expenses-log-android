package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseWithCategoryEntity

@Dao
interface ExpenseDao {

  @Query(
      """
        SELECT expense.title, expense.uuid, expense.amount, category.title AS category_title, expense.created
        FROM expense 
        JOIN category 
        on category.uuid = expense.category_uuid
        JOIN date_filter_time_stamp
        WHERE created BETWEEN 
            date_filter_time_stamp.start AND 
            date_filter_time_stamp.finish
        ORDER BY expense.created DESC    
    """,
  )
  fun expenses(): Flow<List<ExpenseWithCategoryEntity>>

  @Query(
      """
        INSERT INTO expense (title, amount, uuid, category_uuid, created)
        SELECT :title, :amount, :uuid, category.uuid, :created
        FROM category
        WHERE category.title = :category
    """,
  )
  @Transaction
  suspend fun insert(
      title: String?,
      amount: Double,
      category: String,
      uuid: UUID = UUID.randomUUID(),
      created: Instant = Instant.now(),
  )

  @Query(
      """
            DELETE from expense WHERE uuid = :uuid
        """,
  )
  @Transaction
  suspend fun delete(
      uuid: UUID,
  )
}
