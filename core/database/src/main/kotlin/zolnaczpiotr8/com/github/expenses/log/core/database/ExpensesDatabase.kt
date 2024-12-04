package zolnaczpiotr8.com.github.expenses.log.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import zolnaczpiotr8.com.github.expenses.log.core.database.converters.BigDecimalConverter
import zolnaczpiotr8.com.github.expenses.log.core.database.converters.UuidConverter
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.core.database.model.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseEntity

@Database(
    version = 1,
    entities = [
        CategoryEntity::class,
        ExpenseEntity::class,
    ],
)
@TypeConverters(
    UuidConverter::class,
    BigDecimalConverter::class,
)
internal abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
}
