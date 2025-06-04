package zolnaczpiotr8.com.github.expenses.log.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import zolnaczpiotr8.com.github.expenses.log.core.database.converters.BigDecimalConverter
import zolnaczpiotr8.com.github.expenses.log.core.database.converters.InstantConverter
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ShowEmptyCategoriesDao
import zolnaczpiotr8.com.github.expenses.log.core.database.model.ShowEmptyCategoriesEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.category.CategoryEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.date.filter.DateFilterTimeStampEntity
import zolnaczpiotr8.com.github.expenses.log.core.database.model.expense.ExpenseEntity

@Database(
    version = 1,
    entities = [
        CategoryEntity::class,
        ExpenseEntity::class,
        DateFilterEntity::class,
        ShowEmptyCategoriesEntity::class,
    ],
    views = [
        DateFilterTimeStampEntity::class,
    ],
)
@TypeConverters(
    BigDecimalConverter::class,
    InstantConverter::class,
)
abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun dateFilterDao(): DateFilterDao
    abstract fun showEmptyCategoriesDao(): ShowEmptyCategoriesDao
}
