package zolnaczpiotr8.com.github.expenses.log.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import zolnaczpiotr8.com.github.expenses.log.data.categories.CategoryAggregator

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

  @Provides fun provideCategoryAggregator() = CategoryAggregator()
}
