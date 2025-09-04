package zolnaczpiotr8.com.github.expenses.log.ui.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import zolnaczpiotr8.com.github.expenses.log.ui.home.view.model.ExpensesByDateTransformer

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

  @Provides fun provideExpensesByDateTransformer() = ExpensesByDateTransformer()
}
