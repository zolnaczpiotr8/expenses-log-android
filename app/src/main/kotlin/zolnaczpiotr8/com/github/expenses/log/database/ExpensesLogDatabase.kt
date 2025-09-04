package zolnaczpiotr8.com.github.expenses.log.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import zolnaczpiotr8.com.github.expenses.log.database.converters.InstantConverter
import zolnaczpiotr8.com.github.expenses.log.database.converters.UuidConverter
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.ShowEmptyCategoriesDao
import zolnaczpiotr8.com.github.expenses.log.database.entities.ShowEmptyCategoriesEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterTimeStampEntity
import zolnaczpiotr8.com.github.expenses.log.database.entities.expense.ExpenseEntity

@Database(
    version = 1,
    entities =
        [
            CategoryEntity::class,
            ExpenseEntity::class,
            DateFilterEntity::class,
            ShowEmptyCategoriesEntity::class,
        ],
    views =
        [
            DateFilterTimeStampEntity::class,
        ],
)
@TypeConverters(InstantConverter::class, UuidConverter::class)
abstract class ExpensesLogDatabase : RoomDatabase() {

  abstract fun categoryDao(): CategoryDao

  abstract fun expenseDao(): ExpenseDao

  abstract fun dateFilterDao(): DateFilterDao

  abstract fun showEmptyCategoriesDao(): ShowEmptyCategoriesDao
}
