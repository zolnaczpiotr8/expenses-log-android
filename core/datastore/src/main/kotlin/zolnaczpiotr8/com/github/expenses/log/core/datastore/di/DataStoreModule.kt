package zolnaczpiotr8.com.github.expenses.log.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        @ApplicationContext context: Context,
        serializer: SettingsSerializer,
    ): DataStore<SettingsProto> = DataStoreFactory.create(
        serializer = serializer,
        corruptionHandler = ReplaceFileCorruptionHandler {
            SettingsProto.getDefaultInstance()
        },
        produceFile = {
            context.dataStoreFile("settings.pb")
        },
    )
}
