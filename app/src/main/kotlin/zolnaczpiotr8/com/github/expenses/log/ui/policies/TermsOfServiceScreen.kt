package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

private const val PIOTR_ZOLNACZ = "Piotr Żołnacz"

@Composable
fun TermsOfServiceScreen(
    onGoBackClick: () -> Unit = {},
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      topBar = {
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(stringResource(R.string.terms_of_service)) },
            navigationIcon = {
              GoBackIconButton(
                  onClick = onGoBackClick,
              )
            },
        )
      },
  ) { paddingValues ->
    Column(
        modifier =
            Modifier.padding(paddingValues)
                .padding(Margins.compact)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x4),
    ) {
      PolicySection(
          title = stringResource(R.string.terms_of_service_about_title),
          body = stringResource(R.string.terms_of_service_about_body),
      )
      PolicySection(
          title = stringResource(R.string.terms_of_service_use_title),
          body = stringResource(R.string.terms_of_service_use_body),
      )
      PolicySection(
          title = stringResource(R.string.policy_your_data_stays_with_you_title),
          body = stringResource(R.string.terms_of_service_your_data_stays_with_you_body),
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_intellectual_property_title),
          body = stringResource(R.string.terms_of_service_intellectual_property_body),
          footer = PIOTR_ZOLNACZ,
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_disclaimer_of_warranties_title),
          body = stringResource(R.string.terms_of_service_disclaimer_of_warranties_body),
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_limitation_of_liability_title),
          body = stringResource(R.string.terms_of_service_limitation_of_liability_body),
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_termination_title),
          body = stringResource(R.string.terms_of_service_termination_body),
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_governing_law_title),
          body = stringResource(R.string.terms_of_service_governing_law_body),
      )

      PolicySection(
          title = stringResource(R.string.terms_of_service_changes_title),
          body = stringResource(R.string.terms_of_service_changes_body),
      )

      PolicySection(
          title = stringResource(R.string.policy_contact_information_title),
          body = stringResource(R.string.terms_of_service_contact_information_body),
          footer = eMail(),
      )

      PolicySection(
          title = stringResource(R.string.policy_effective_date_title),
          body = stringResource(R.string.terms_of_service_effective_date_body),
          footer = rememberEffectiveDate(),
      )

      Spacer(
          Modifier.windowInsetsBottomHeight(WindowInsets.safeContent),
      )
    }
  }
}
