package zolnaczpiotr8.com.github.expenses.log.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity

@Dao
interface DateFilterDao {

    @Query(
        """
            SELECT * 
            FROM date_filter
        """,
    )
    fun dateFilter(): Flow<DateFilterEntity?>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
    )
    suspend fun insert(
        entity: DateFilterEntity,
    )
}
