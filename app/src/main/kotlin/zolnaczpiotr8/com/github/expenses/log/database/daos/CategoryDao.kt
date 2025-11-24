package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity

@Dao
internal interface CategoryDao {

  @Query(
      """
        SELECT title
        FROM category
        ORDER BY title
    """,
  )
  fun categoriesTitles(): Flow<List<String>>

  @Query(
      """
        SELECT MIN(category.uuid) AS uuid,
        category.title AS title,
        TOTAL(expense.amount) AS total_amount
        FROM category 
        JOIN date_filter_time_stamp
        LEFT JOIN expense
        ON expense.category_uuid = category.uuid
        AND expense.created BETWEEN 
            date_filter_time_stamp.start AND 
            date_filter_time_stamp.finish
        LEFT JOIN show_empty_categories
        GROUP BY category.title
        HAVING ifnull(MAX(expense.amount), 0) > 0
            OR ifnull(MIN(show_empty_categories.value), 0) > 0
        ORDER BY category.title
    """,
  )
  fun categories(): Flow<List<CategoryTotalEntity>>

  @Insert(
      onConflict = OnConflictStrategy.IGNORE,
  )
  suspend fun insert(entity: CategoryEntity)

  @Query(
      "DELETE from category WHERE uuid = :uuid",
  )
  @Transaction
  suspend fun delete(
      uuid: UUID,
  )
}
