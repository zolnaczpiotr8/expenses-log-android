package zolnaczpiotr8.com.github.expenses.log.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zolnaczpiotr8.com.github.expenses.log.core.database.ExpensesLogDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesExpensesLogDatabase(context: Context): ExpensesLogDatabase =
        Room.databaseBuilder(
            context,
            ExpensesLogDatabase::class.java,
            "expenses-log-database",
        ).build()
}
