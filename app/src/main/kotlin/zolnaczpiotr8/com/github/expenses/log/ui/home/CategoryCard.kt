package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings

val expenseCategoryCardWidth = 150.dp

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onMenuClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = IncrementalPaddings.x4,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.semantics {
                    heading()
                }.weight(
                    weight = 1.0f,
                    fill = false,
                ).padding(
                    top = IncrementalPaddings.x4,
                ),
                text = category.title,
                style = MaterialTheme.typography.titleMedium,
            )
            MenuIconButton(
                modifier = Modifier.padding(
                    end = IncrementalPaddings.x1,
                    top = IncrementalPaddings.x1,
                ),
                onClick = onMenuClick,
            )
        }
        Text(
            modifier = Modifier.padding(
                horizontal = IncrementalPaddings.x4,
            ).padding(
                top = IncrementalPaddings.x1,
                bottom = IncrementalPaddings.x4,
            ),
            text = category.totalAmount.toString(),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
