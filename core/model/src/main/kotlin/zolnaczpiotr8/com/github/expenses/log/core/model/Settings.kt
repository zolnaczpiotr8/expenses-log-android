package zolnaczpiotr8.com.github.expenses.log.core.model

import android.icu.util.Currency
import android.icu.util.ULocale
import android.util.Log

private val TAG = Settings::class.java.name

data class Settings(
    val currencyCode: String,
    val showEmptyCategories: Boolean,
) {

    val currency: Currency by lazy {
        try {
            Currency.getInstance(currencyCode)
        } catch (exception: NumberFormatException) {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(
                    TAG,
                    exception.message,
                    exception,
                )
            }
            Currency.getInstance(ULocale.getDefault())
        }
    }
}
