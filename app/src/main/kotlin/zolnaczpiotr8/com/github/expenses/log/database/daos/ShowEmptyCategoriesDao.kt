package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.ShowEmptyCategoriesEntity

@Dao
internal interface ShowEmptyCategoriesDao {

  @Query(
      """
            SELECT * 
            FROM show_empty_categories
        """,
  )
  fun showEmptyCategories(): Flow<ShowEmptyCategoriesEntity?>

  @Insert(
      onConflict = OnConflictStrategy.REPLACE,
  )
  suspend fun insert(
      entity: ShowEmptyCategoriesEntity,
  )
}
