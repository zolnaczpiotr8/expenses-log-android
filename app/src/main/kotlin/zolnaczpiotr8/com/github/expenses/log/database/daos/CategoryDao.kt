package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryTotalEntity

@Dao
interface CategoryDao {

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
        SELECT category.uuid AS uuid,
        category.title AS title,
        SUM(ifnull(expense.amount,0)) AS total_amount
        FROM category 
        JOIN date_filter_time_stamp
        LEFT JOIN expense
        ON expense.category_uuid = category.uuid
        AND CAST(expense.created/1000 AS INT) BETWEEN 
            date_filter_time_stamp.start AND 
            date_filter_time_stamp.finish
        LEFT JOIN show_empty_categories
        GROUP BY category.uuid, category.title
        HAVING SUM(ifnull(expense.amount,0)) + MIN(ifnull(show_empty_categories.value, 0)) > 0
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
    suspend fun delete(
        uuid: String,
    )
}
