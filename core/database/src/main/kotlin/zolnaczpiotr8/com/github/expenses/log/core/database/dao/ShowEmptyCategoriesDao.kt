package zolnaczpiotr8.com.github.expenses.log.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.core.database.model.ShowEmptyCategoriesEntity

@Dao
interface ShowEmptyCategoriesDao {

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
