package zolnaczpiotr8.com.github.expenses.log.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zolnaczpiotr8.com.github.expenses.log.core.database.ExpensesDatabase
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.core.database.dao.ExpenseDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun provideCategoryDao(
        database: ExpensesDatabase,
    ): CategoryDao = database.categoryDao()

    @Provides
    fun provideExpenseDao(
        database: ExpensesDatabase,
    ): ExpenseDao = database.expenseDao()
}
