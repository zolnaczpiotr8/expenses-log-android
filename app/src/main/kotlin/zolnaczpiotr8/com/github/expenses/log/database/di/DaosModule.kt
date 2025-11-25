package zolnaczpiotr8.com.github.expenses.log.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase
import zolnaczpiotr8.com.github.expenses.log.database.daos.CategoryDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.DateFilterDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.ExpenseDao
import zolnaczpiotr8.com.github.expenses.log.database.daos.ShowEmptyCategoriesDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

  @Provides
  fun provideShowEmptyCategoriesDao(
      database: ExpensesLogDatabase,
  ): ShowEmptyCategoriesDao = database.showEmptyCategoriesDao()

  @Provides
  fun provideDateFilterDao(
      database: ExpensesLogDatabase,
  ): DateFilterDao = database.dateFilterDao()

  @Provides
  fun provideCategoryDao(
      database: ExpensesLogDatabase,
  ): CategoryDao = database.categoryDao()

  @Provides
  fun provideExpenseDao(
      database: ExpensesLogDatabase,
  ): ExpenseDao = database.expenseDao()
}
