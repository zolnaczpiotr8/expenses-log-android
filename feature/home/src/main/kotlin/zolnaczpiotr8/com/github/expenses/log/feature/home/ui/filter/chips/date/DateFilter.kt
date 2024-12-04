package zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date

import kotlinx.datetime.Instant

sealed interface DateFilter {

    data object AnyDate : DateFilter

    data object Month : DateFilter

    data object Quarter : DateFilter

    data object Year : DateFilter

    data class Custom(
        val start: Instant,
        val end: Instant,
    ) : DateFilter
}
