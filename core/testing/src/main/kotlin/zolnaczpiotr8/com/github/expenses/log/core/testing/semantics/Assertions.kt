package zolnaczpiotr8.com.github.expenses.log.core.testing.semantics

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert

fun SemanticsNodeInteraction.assertHasOnClickLabel(label: String): SemanticsNodeInteraction =
    assert(
        SemanticsMatcher("onClickLabel is set correctly") {
            it.config.getOrNull(SemanticsActions.OnClick)?.label == label
        },
    )

fun SemanticsNodeInteraction.assertHasButtonRole(): SemanticsNodeInteraction =
    assert(
        SemanticsMatcher("role is button") {
            it.config.getOrNull(SemanticsProperties.Role) == Role.Button
        },
    )
