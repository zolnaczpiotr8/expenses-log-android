package zolnaczpiotr8.com.github.expenses.log.ui.home

import android.icu.text.DateFormat
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.LocalDate
import java.util.Calendar
import java.util.UUID
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.model.CategoriesSummary
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.model.Expense
import zolnaczpiotr8.com.github.expenses.log.ui.components.Measurements
import zolnaczpiotr8.com.github.expenses.log.ui.home.view.model.HomeViewModel
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewCategoryClick: () -> Unit,
    onNewExpenseClick: (String?) -> Unit,
    onSettingsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfServiceClick: () -> Unit,
) {
  val categories by viewModel.categoriesSummary.collectAsStateWithLifecycle()
  val showEmptyCategoriesFilter by viewModel.showEmptyCategoriesFilter.collectAsStateWithLifecycle()
  val dateFilter by viewModel.dateFilter.collectAsStateWithLifecycle()
  val expenses by viewModel.expenses.collectAsStateWithLifecycle()
  val agreedToTerms by viewModel.agreedToTerms.collectAsStateWithLifecycle()
  HomeScreen(
      onDateFilterClick = viewModel::onDateFilterClick,
      showEmptyCategoriesFilter = showEmptyCategoriesFilter,
      dateFilter = dateFilter,
      onShowEmptyCategoriesFilterClick = viewModel::onShowEmptyCategoriesFilterClick,
      categoriesSummary = categories,
      expenses = expenses,
      agreedToTerms = agreedToTerms,
      onAgreeToTermsClick = viewModel::onAgreeToTermsClick,
      onCategoryDeleteClicked = viewModel::onCategoryDeleteClicked,
      onExpenseDeleteClicked = viewModel::onExpenseDeleteClicked,
      onNewCategoryClick = onNewCategoryClick,
      onNewExpenseClick = onNewExpenseClick,
      onSettingsClick = onSettingsClick,
      onPrivacyPolicyClick = onPrivacyPolicyClick,
      onTermsOfServiceClick = onTermsOfServiceClick,
  )
}

@Composable
fun HomeScreen(
    dateFilter: DateFilter = DateFilter.Any,
    onDateFilterClick: (DateFilter) -> Unit = {},
    showEmptyCategoriesFilter: Boolean = false,
    onShowEmptyCategoriesFilterClick: (Boolean) -> Unit = {},
    categoriesSummary: CategoriesSummary? = null,
    expenses: Map<LocalDate, List<Expense>> = emptyMap(),
    agreedToTerms: Boolean = false,
    onAgreeToTermsClick: () -> Unit = {},
    onExpenseDeleteClicked: (UUID) -> Unit = {},
    onCategoryDeleteClicked: (UUID) -> Unit = {},
    onNewCategoryClick: () -> Unit = {},
    onNewExpenseClick: (String?) -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsOfServiceClick: () -> Unit = {},
) {
  if (agreedToTerms.not()) {
    FirstLaunchAgreementDialog(
        onTermsOfServiceClick = onTermsOfServiceClick,
        onAgreeClick = onAgreeToTermsClick,
    )
  }

  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  val scaffoldState = rememberBottomSheetScaffoldState()
  val scope = rememberCoroutineScope()
  BottomSheetScaffold(
      scaffoldState = scaffoldState,
      modifier =
          // TODO: check later (on newer material 3 version) if this workaround with insets is sill
          // needed
          Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
              .windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Bottom)),
      sheetContent = {
        if (expenses.isNotEmpty()) {
          Text(
              modifier =
                  Modifier.semantics { heading() }
                      .padding(
                          start = Measurements.ListItem.startPadding,
                      )
                      .padding(
                          vertical = Measurements.ListItem.verticalPadding,
                      ),
              text = stringResource(R.string.expenses_title),
              style = MaterialTheme.typography.titleLarge,
          )
          LazyColumn(
              verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
          ) {
            expenses.forEach { (date, expenses) ->
              stickyHeader(
                  key = date,
                  contentType = ExpensesContentType.Header,
                  content = {
                    Column {
                      ListItem(
                          modifier = Modifier.semantics { heading() }.animateItem(),
                          headlineContent = { Text(relativeFormat(date)) },
                      )
                      HorizontalDivider()
                    }
                  },
              )

              items(
                  items = expenses,
                  key = Expense::uuid,
                  contentType = { ExpensesContentType.Expense },
              ) { expense ->
                val dialogVisibilityState = rememberSaveable { mutableStateOf(false) }

                ExpenseListItem(
                    modifier = Modifier.animateItem(),
                    expense = expense,
                    onDeleteClick = {
                      dialogVisibilityState.value = true
                      true
                    },
                )

                DeleteDialog(
                    isVisible = dialogVisibilityState.value,
                    onHide = { dialogVisibilityState.value = false },
                    text = stringResource(R.string.delete_expense_text),
                    onDeleteClick = { onExpenseDeleteClicked(expense.uuid) },
                )
              }
            }

            item {
              Spacer(
                  Modifier.windowInsetsBottomHeight(WindowInsets.safeContent),
              )
            }
          }
        }
      },
      topBar = {
        CenterAlignedTopAppBar(
            title = {
              Text(
                  text = stringResource(R.string.application_label),
                  modifier = Modifier.semantics { hideFromAccessibility() },
              )
            },
            actions = {
              val menuSheetState = rememberModalBottomSheetState()

              MenuIconButton { scope.launch { menuSheetState.show() } }

              val policiesSheetState = rememberModalBottomSheetState()
              MainMenuModalBottomSheet(
                  state = menuSheetState,
                  onNewCategoryClick = onNewCategoryClick,
                  onNewExpenseClick = { onNewExpenseClick(null) },
                  onShowExpensesClick = {
                    scope.launch { scaffoldState.bottomSheetState.expand() }
                  },
                  onSettingsClick = onSettingsClick,
                  onPoliciesClick = { scope.launch { policiesSheetState.show() } },
              )

              PoliciesModalBottomSheet(
                  state = policiesSheetState,
                  onPrivacyPolicyClick = onPrivacyPolicyClick,
                  onTermsOfServiceClick = onTermsOfServiceClick,
              )
            },
            scrollBehavior = scrollBehavior,
        )
      },
  ) { paddingValues ->
    Column(
        Modifier.windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Horizontal))
            .fillMaxHeight(),
    ) {
      Column(
          Modifier.padding(Margins.compact).consumeWindowInsets(Margins.compact),
          verticalArrangement =
              Arrangement.spacedBy(
                  IncrementalPaddings.x1,
              ),
      ) {
        Text(
            modifier = Modifier.semantics { heading() },
            text = stringResource(R.string.filters_title),
            style = MaterialTheme.typography.titleMedium,
        )
        FlowRow(
            horizontalArrangement =
                Arrangement.spacedBy(
                    IncrementalPaddings.x1,
                ),
        ) {
          val sheetState = rememberDateFilterSheetState(dateFilter)
          DateFilterChip(
              filter = dateFilter,
              onClick = { scope.launch { sheetState.show() } },
          )

          val datePickerVisibilityState = rememberSaveable { mutableStateOf(false) }

          DateFilterModalBottomSheet(
              state = sheetState,
              onYearClicked = { onDateFilterClick(DateFilter.Year) },
              onAnyDateClicked = { onDateFilterClick(DateFilter.Any) },
              onMonthClicked = { onDateFilterClick(DateFilter.Month) },
              onCustomClicked = { datePickerVisibilityState.value = true },
          )

          if (datePickerVisibilityState.value) {
            DateFilterPickerDialog(
                initial = dateFilter as? DateFilter.Custom,
                onDismiss = { datePickerVisibilityState.value = false },
                onPicked = {
                  onDateFilterClick(it)
                  datePickerVisibilityState.value = false
                },
            )
          }

          ShowEmptyCategoriesFilterChip(
              isSelected = showEmptyCategoriesFilter,
              onClick = { onShowEmptyCategoriesFilterClick(showEmptyCategoriesFilter.not()) },
          )
        }
        Spacer(
            Modifier.height(IncrementalPaddings.x3),
        )
        Crossfade(categoriesSummary?.categories?.isNotEmpty() == true) {
          if (it) {
            Categories(
                onCategoryDeleteClicked = onCategoryDeleteClicked,
                categoriesSummary = categoriesSummary,
                bottomPadding = paddingValues.calculateBottomPadding(),
                onNewExpenseClick = onNewExpenseClick,
            )
          } else {
            NoExpenses()
          }
        }
      }
    }
  }
}

@Composable
private fun relativeFormat(localDate: LocalDate): String =
    remember(localDate) {
      val date =
          Calendar.Builder()
              .setDate(localDate.year, localDate.monthValue.dec(), localDate.dayOfMonth)
              .build()
              .time
      DateFormat.getDateInstance(DateFormat.RELATIVE_LONG).format(date)
    }

@Composable
fun NoExpenses(
    modifier: Modifier = Modifier,
) {
  Box(modifier.fillMaxSize()) {
    Text(
        modifier = modifier.align(Alignment.Center),
        text = stringResource(R.string.no_expenses_title),
        style = MaterialTheme.typography.titleMedium,
    )
  }
}

@Composable
private fun Categories(
    modifier: Modifier = Modifier,
    bottomPadding: Dp,
    categoriesSummary: CategoriesSummary?,
    onCategoryDeleteClicked: (UUID) -> Unit = {},
    onNewExpenseClick: (String?) -> Unit = {},
) {
  val scope = rememberCoroutineScope()
  if (categoriesSummary == null) {
    return
  }
  LazyVerticalStaggeredGrid(
      columns = StaggeredGridCells.Adaptive(expenseCategoryCardWidth),
      verticalItemSpacing = IncrementalPaddings.x1,
      horizontalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
      modifier = modifier.fillMaxHeight(),
  ) {
    item(span = StaggeredGridItemSpan.FullLine, contentType = CategoriesContentType.Text) {
      Text(
          modifier = Modifier.semantics { heading() },
          text = stringResource(R.string.total_amount_title),
          style = MaterialTheme.typography.titleMedium,
      )
    }
    item(contentType = CategoriesContentType.Text) {
      val totalAmount =
          remember(categoriesSummary.totalAmount) {
            categoriesSummary.totalAmount.toFormattedString()
          }
      Text(
          text = totalAmount,
          style = MaterialTheme.typography.bodyMedium,
      )
    }
    item(span = StaggeredGridItemSpan.FullLine, contentType = CategoriesContentType.Spacer) {
      Spacer(
          Modifier.height(IncrementalPaddings.x3),
      )
    }
    item(span = StaggeredGridItemSpan.FullLine, contentType = CategoriesContentType.Text) {
      Text(
          modifier = Modifier.semantics { heading() },
          text = stringResource(R.string.expenses_categories_title),
          style = MaterialTheme.typography.titleMedium,
      )
    }
    items(
        items = categoriesSummary.categories,
        key = Category::uuid,
        contentType = { CategoriesContentType.Category },
    ) { category ->
      val menuState = rememberCategoryMenuSheetState(category.title)
      CategoryCard(
          modifier = Modifier.animateItem(),
          category = category,
          onMenuClick = { scope.launch { menuState.show() } },
      )

      val deleteDialogVisibility = rememberSaveable { mutableStateOf(false) }
      CategoryMenuModalBottomSheet(
          state = menuState,
          onNewExpenseClick = { onNewExpenseClick(category.title) },
          onDeleteClick = { deleteDialogVisibility.value = true },
      )

      DeleteDialog(
          isVisible = deleteDialogVisibility.value,
          onHide = { deleteDialogVisibility.value = false },
          text = stringResource(R.string.delete_expense_category_text),
          onDeleteClick = { onCategoryDeleteClicked(category.uuid) },
      )
    }

    item(span = StaggeredGridItemSpan.FullLine, contentType = CategoriesContentType.Spacer) {
      Spacer(
          Modifier.height(bottomPadding),
      )
    }
  }
}
