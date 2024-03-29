package zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.more.options

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import zolnaczpiotr8.com.github.expenses.log.core.testing.semantics.assertHasOnClickLabel
import zolnaczpiotr8.com.github.expenses.log.core.ui.R

class MoreOptionsBottomSheetTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun accessibility_onClickLabel_isCorrect() {
        // GIVEN
        composeTestRule.setContent {
            val sheetStateDummy =
                SheetState(
                    false,
                    LocalDensity.current,
                    SheetValue.PartiallyExpanded,
                    {
                        true
                    },
                    false,
                )
            MoreOptionsBottomSheet(
                onClick = {
                },
                onDismissRequest = {
                },
                sheetState = sheetStateDummy,
                content = {
                },
            )
        }

        // WHEN
        // NOP

        // THEN
        val menuDescription = composeTestRule.activity.getString(R.string.more_options_description)
        val onClickLabel =
            composeTestRule.activity.getString(
                R.string.more_options_bottom_sheet_on_click_label,
            )
        composeTestRule
            .onNodeWithContentDescription(menuDescription)
            .assertHasOnClickLabel(onClickLabel)
    }
}
