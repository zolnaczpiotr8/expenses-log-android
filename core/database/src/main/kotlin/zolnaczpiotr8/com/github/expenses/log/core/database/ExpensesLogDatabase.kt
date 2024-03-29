package zolnaczpiotr8.com.github.expenses.log.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import zolnaczpiotr8.com.github.expenses.log.core.database.converters.ZonedDateTimeConverter
import zolnaczpiotr8.com.github.expenses.log.core.database.model.ExpenseEntity

@Database(
    entities = [
        ExpenseEntity::class,
    ],
    version = 1,
)
@TypeConverters(ZonedDateTimeConverter::class)
internal abstract class ExpensesLogDatabase : RoomDatabase()
