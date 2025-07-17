package zolnaczpiotr8.com.github.expenses.log.database.entities.date.filter

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "date_filter_time_stamp",
    value =
        """
        SELECT (
                CASE title
                    WHEN 'Year' THEN unixepoch('now', 'start of year')
                    WHEN 'Custom' THEN unixepoch(CAST(date_filter.start/1000 AS INT), 'unixepoch', 'start of day')
                    WHEN 'Any' THEN 0
                    ELSE unixepoch('now', 'start of month')
                END
            ) as start, 
            (
                CASE title
                    WHEN 'Year' THEN unixepoch('now', 'start of year', '+1 years', '-1 seconds')
                    WHEN 'Custom' THEN unixepoch(CAST(date_filter.finish/1000 AS INT), 'unixepoch','start of day' ,'+1 days', '-1 seconds')
                    WHEN 'Any' THEN 9223372036854775807
                    ELSE unixepoch('now', 'start of month', '+1 months', '-1 seconds')
                END
            ) as finish 
            FROM date_filter
         UNION   
         SELECT unixepoch('now', 'start of month') as start,
            unixepoch('now', 'start of month', '+1 months', '-1 seconds') as finish 
         FROM (
            SELECT COUNT(id) as date_filters_count
            FROM date_filter
        ) 
        WHERE date_filters_count == 0
    """,
)
data class DateFilterTimeStampEntity(
    val start: Long,
    val finish: Long,
)
