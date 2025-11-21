package zolnaczpiotr8.com.github.expenses.log.ui.policies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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

@Composable
fun PrivacyPolicyScreen(
    onGoBackClick: () -> Unit = {},
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      topBar = {
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(stringResource(R.string.privacy_policy)) },
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
          title = stringResource(R.string.policy_your_data_stays_with_you_title),
          body = stringResource(R.string.privacy_policy_your_data_stays_with_you_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_local_only_data_storage_title),
          body = stringResource(R.string.privacy_policy_local_only_data_storage_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_no_third_party_services_title),
          body = stringResource(R.string.privacy_policy_no_third_party_services_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_data_control_and_deletion_title),
          body = stringResource(R.string.privacy_policy_data_control_and_deletion_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_children_privacy_title),
          body = stringResource(R.string.privacy_policy_children_privacy_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_your_rights_title),
          body = stringResource(R.string.privacy_policy_your_rights_body),
      )
      PolicySection(
          title = stringResource(R.string.privacy_policy_changes_title),
          body = stringResource(R.string.privacy_policy_changes_body),
      )
      PolicySection(
          title = stringResource(R.string.policy_contact_information_title),
          body = stringResource(R.string.privacy_policy_contact_information_body),
          footer = eMail(),
      )

      PolicySection(
          title = stringResource(R.string.policy_effective_date_title),
          body = stringResource(R.string.privacy_policy_effective_date_body),
          footer = rememberEffectiveDate(),
      )

      Spacer(
          Modifier.windowInsetsBottomHeight(WindowInsets.systemBars),
      )
    }
  }
}
