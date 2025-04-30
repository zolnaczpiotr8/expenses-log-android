package zolnaczpiotr8.com.github.expenses.log.core.datastore.test.doubles.di

import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsProto
import zolnaczpiotr8.com.github.expenses.log.core.datastore.SettingsSerializer
import zolnaczpiotr8.com.github.expenses.log.core.datastore.di.DataStoreModule
import zolnaczpiotr8.com.github.expenses.log.core.datastore.test.doubles.FakeDataStore
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataStoreModule::class],
)
object TestDataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        serializer: SettingsSerializer,
    ): DataStore<SettingsProto> = FakeDataStore(
        SettingsProto.getDefaultInstance().toBuilder()
            .setCurrencyCode("USD")
            .build(),
    )
}
