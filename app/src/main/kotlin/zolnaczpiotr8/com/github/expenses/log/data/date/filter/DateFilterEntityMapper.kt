package zolnaczpiotr8.com.github.expenses.log.data.date.filter

import javax.inject.Inject
import zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter.DateFilterEntity
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter

class DateFilterEntityMapper @Inject constructor() {

  fun map(entity: DateFilterEntity?): DateFilter =
      when (entity?.title) {
        DateFilter.Any.NAME -> DateFilter.Any
        DateFilter.Month.NAME -> DateFilter.Month
        DateFilter.Year.NAME -> DateFilter.Year
        DateFilter.Custom.NAME -> {
          val start = entity.start ?: return DateFilter.Default
          val end = entity.finish ?: return DateFilter.Default
          DateFilter.Custom(
              start = start,
              end = end,
          )
        }

        else -> DateFilter.Default
      }
}
