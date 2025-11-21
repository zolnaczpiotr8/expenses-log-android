package zolnaczpiotr8.com.github.expenses.log.ui.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {

  @Provides fun provideMainCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
