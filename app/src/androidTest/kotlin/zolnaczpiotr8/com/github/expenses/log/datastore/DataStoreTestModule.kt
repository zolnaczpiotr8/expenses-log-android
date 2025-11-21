package zolnaczpiotr8.com.github.expenses.log.datastore

import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DataStoreModule::class])
object DataStoreTestModule {

  @Provides
  @Singleton
  fun provideSettingsDataStore(): DataStore<SettingsProto> =
      FakeDataStore(SettingsProto.getDefaultInstance())
}
