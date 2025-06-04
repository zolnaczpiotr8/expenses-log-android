package zolnaczpiotr8.com.github.expenses.log.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zolnaczpiotr8.com.github.expenses.log.core.database.ExpensesDatabase
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ShowEmptyCategoriesDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideShowEmptyCategoriesDao(
        database: ExpensesDatabase,
    ): ShowEmptyCategoriesDao = database.showEmptyCategoriesDao()

    @Provides
    fun provideDateFilterDao(
        database: ExpensesDatabase,
    ): DateFilterDao = database.dateFilterDao()

    @Provides
    fun provideCategoryDao(
        database: ExpensesDatabase,
    ): CategoryDao = database.categoryDao()

    @Provides
    fun provideExpenseDao(
        database: ExpensesDatabase,
    ): ExpenseDao = database.expenseDao()
}
