package zolnaczpiotr8.com.github.expenses.log.database.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import zolnaczpiotr8.com.github.expenses.log.database.ExpensesLogDatabase

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
object DatabaseTestModule {

  @Provides
  @Singleton
  fun provideDatabase(): ExpensesLogDatabase =
      Room.inMemoryDatabaseBuilder(
              context = ApplicationProvider.getApplicationContext(),
              klass = ExpensesLogDatabase::class.java,
          )
          .build()
}
