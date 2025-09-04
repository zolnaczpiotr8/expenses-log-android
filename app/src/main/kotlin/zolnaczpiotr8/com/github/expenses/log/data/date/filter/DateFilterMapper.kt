package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import javax.inject.Inject
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterMapper @Inject constructor() {

  fun toEntity(filter: DateFilter): DateFilterEntity =
      when (filter) {
        is DateFilter.Any ->
            DateFilterEntity(
                title = DateFilter.Any.NAME,
            )

        is DateFilter.Month ->
            DateFilterEntity(
                title = DateFilter.Month.NAME,
            )

        is DateFilter.Year ->
            DateFilterEntity(
                title = DateFilter.Year.NAME,
            )

        is DateFilter.Custom ->
            DateFilterEntity(
                title = DateFilter.Custom.NAME,
                start = filter.start,
                finish = filter.end,
            )
      }
}
