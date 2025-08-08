package zolnaczpiotr8.com.github.expenses.log.ui.privacy.policy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.ui.components.buttons.icon.buttons.GoBackIconButton
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
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
        verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x4)) {
          Section(
              title = stringResource(R.string.privacy_policy_your_data_stays_with_you_title),
              body = stringResource(R.string.privacy_policy_your_data_stays_with_you_body))
          Section(
              title = stringResource(R.string.privacy_policy_local_only_data_storage_title),
              body = stringResource(R.string.privacy_policy_local_only_data_storage_body))
          Section(
              title = stringResource(R.string.privacy_policy_no_third_party_services_title),
              body = stringResource(R.string.privacy_policy_no_third_party_services_body))
          Section(
              title = stringResource(R.string.privacy_policy_data_control_and_deletion_title),
              body = stringResource(R.string.privacy_policy_data_control_and_deletion_body))
          Section(
              title = stringResource(R.string.privacy_policy_children_privacy_title),
              body = stringResource(R.string.privacy_policy_children_privacy_body))
          Section(
              title = stringResource(R.string.privacy_policy_your_rights_title),
              body = stringResource(R.string.privacy_policy_your_rights_body))
          Section(
              title = stringResource(R.string.privacy_policy_changes_title),
              body = stringResource(R.string.privacy_policy_changes_body))
          Section(
              title = stringResource(R.string.privacy_policy_contact_information_title),
              body = stringResource(R.string.privacy_policy_contact_information_body),
              footer =
                  buildAnnotatedString {
                    val email = "simkovadagmar80@gmail.com"
                    withLink(
                        LinkAnnotation.Url(
                            url =
                                "mailto:$email?Subject=${stringResource(R.string.privacy_policy_email_subject, stringResource(R.string.application_label))}",
                            styles =
                                TextLinkStyles(
                                    style =
                                        MaterialTheme.typography.bodyMedium
                                            .toSpanStyle()
                                            .copy(
                                                textDecoration = TextDecoration.Underline,
                                                color = MaterialTheme.colorScheme.tertiary)),
                        )) {
                          append(email)
                        }
                  })

          Section(
              title = stringResource(R.string.privacy_policy_effective_date_title),
              body = stringResource(R.string.privacy_policy_effective_date_body),
              footer = "2025-08-01")

          Spacer(
              Modifier.windowInsetsBottomHeight(WindowInsets.systemBars),
          )
        }
  }
}

@Composable
private fun Section(title: String, body: String, footer: String? = null) {
  Section(
      title = title,
      body = body,
      footer = footer?.run(::AnnotatedString),
  )
}

@Composable
private fun Section(title: String, body: String, footer: AnnotatedString?) {
  Column(verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1)) {
    Text(
        modifier = Modifier.semantics { heading() },
        text = title,
        style = MaterialTheme.typography.titleMedium)
    Text(text = body, style = MaterialTheme.typography.bodyMedium)
    footer?.let { Text(text = footer, style = MaterialTheme.typography.bodyMedium) }
  }
}
