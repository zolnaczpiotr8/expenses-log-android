package zolnaczpiotr8.com.github.expenses.log.ui.home

import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import android.icu.util.CurrencyAmount
import android.icu.util.ULocale
import android.os.Build

fun CurrencyAmount.toFormattedString(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      NumberFormatter.withLocale(ULocale.getDefault()).format(this).toString()
    } else {
      NumberFormat.getInstance(NumberFormat.CURRENCYSTYLE).format(this)
    }
