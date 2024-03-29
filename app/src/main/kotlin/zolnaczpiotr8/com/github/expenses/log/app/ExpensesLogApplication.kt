package zolnaczpiotr8.com.github.expenses.log.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class ExpensesLogApplication : Application() {
    // Workaround for https://github.com/google/dagger/issues/3601
    @Inject @ApplicationContext
    lateinit var context: Context
}
