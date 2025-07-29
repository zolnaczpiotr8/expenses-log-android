package zolnaczpiotr8.com.github.expenses.log.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.R
import zolnaczpiotr8.com.github.expenses.log.model.Categories
import zolnaczpiotr8.com.github.expenses.log.model.Category
import zolnaczpiotr8.com.github.expenses.log.model.DateFilter
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.ui.spacing.Margins

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewCategoryClick: () -> Unit = {},
    onNewExpenseClick: (String?) -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
  val categories by viewModel.categories.collectAsStateWithLifecycle()
  val showEmptyCategoriesFilter by viewModel.showEmptyCategoriesFilter.collectAsStateWithLifecycle()
  val dateFilter by viewModel.dateFilter.collectAsStateWithLifecycle()
  val expenses by viewModel.expenses.collectAsStateWithLifecycle()
  HomeScreen(
      onDateFilterClick = viewModel::onDateFilterClick,
      showEmptyCategoriesFilter = showEmptyCategoriesFilter,
      dateFilter = dateFilter,
      onShowEmptyCategoriesFilterClick = viewModel::onShowEmptyCategoriesFilterClick,
      categories = categories,
      expenses = expenses,
      onCategoryDeleteClicked = viewModel::onCategoryDeleteClicked,
      onExpenseDeleteClicked = viewModel::onExpenseDeleteClicked,
      onNewCategoryClick = onNewCategoryClick,
      onNewExpenseClick = onNewExpenseClick,
      onSettingsClick = onSettingsClick,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    dateFilter: DateFilter = DateFilter.Any,
    onDateFilterClick: (DateFilter) -> Unit = {},
    showEmptyCategoriesFilter: Boolean = false,
    onShowEmptyCategoriesFilterClick: (Boolean) -> Unit = {},
    categories: Categories?,
    expenses: ImmutableList<ExpenseItem>,
    onExpenseDeleteClicked: (String) -> Unit = {},
    onCategoryDeleteClicked: (Category) -> Unit = {},
    onNewCategoryClick: () -> Unit = {},
    onNewExpenseClick: (String?) -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  val scaffoldState = rememberBottomSheetScaffoldState()
  val scope = rememberCoroutineScope()
  BottomSheetScaffold(
      scaffoldState = scaffoldState,
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      sheetContent = {
        AnimatedVisibility(expenses.isNotEmpty()) {
          Text(
              modifier =
                  Modifier.semantics { heading() }
                      .padding(
                          start = IncrementalPaddings.x4,
                      )
                      .padding(
                          vertical = IncrementalPaddings.x3,
                      ),
              text = stringResource(R.string.expenses_title),
              style = MaterialTheme.typography.titleLarge,
          )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
            modifier =
                Modifier.semantics {
                  collectionInfo =
                      CollectionInfo(
                          rowCount = expenses.size,
                          columnCount = 1,
                      )
                },
        ) {
          expenses.forEachIndexed { index, expenseItem ->
            when (expenseItem) {
              is ExpenseItem.Header ->
                  stickyHeader(
                      contentType = expenseItem.contentType,
                      key = expenseItem.date.toString(),
                      content = {
                        ListItem(
                            modifier =
                                Modifier.semantics {
                                      heading()
                                      collectionItemInfo =
                                          CollectionItemInfo(
                                              rowSpan = 1,
                                              columnSpan = 1,
                                              columnIndex = 1,
                                              rowIndex = index,
                                          )
                                    }
                                    .animateItem(),
                            headlineContent = { Text(expenseItem.date.toString()) },
                        )
                      },
                  )
              is ExpenseItem.Expense ->
                  item(
                      contentType = expenseItem.contentType,
                      key = expenseItem.uuid,
                  ) {
                    val dialogVisibilityState = rememberSaveable { mutableStateOf(false) }

                    ExpenseListItem(
                        modifier =
                            Modifier.semantics {
                                  collectionItemInfo =
                                      CollectionItemInfo(
                                          rowSpan = 1,
                                          columnSpan = 1,
                                          columnIndex = 1,
                                          rowIndex = index,
                                      )
                                }
                                .animateItem(),
                        expense = expenseItem,
                        onDeleteClick = {
                          dialogVisibilityState.value = true
                          true
                        },
                    )

                    DeleteDialog(
                        isVisible = dialogVisibilityState.value,
                        onHide = { dialogVisibilityState.value = false },
                        text = stringResource(R.string.delete_expense_text),
                        onDeleteClick = { onExpenseDeleteClicked(expenseItem.uuid) },
                    )
                  }
            }
          }

          item {
            AnimatedVisibility(expenses.isNotEmpty()) {
              Spacer(
                  Modifier.windowInsetsBottomHeight(WindowInsets.systemBars).animateContentSize(),
              )
            }
          }
        }
      },
      topBar = {
        CenterAlignedTopAppBar(
            title = {
              val applicationName = stringResource(R.string.application_label)
              Text(
                  text = applicationName,
                  modifier = Modifier.clearAndSetSemantics { testTag = applicationName },
              )
            },
            actions = {
              val sheetState = rememberModalBottomSheetState()

              MenuIconButton { scope.launch { sheetState.show() } }

              MainMenuModalBottomSheet(
                  state = sheetState,
                  onNewCategoryClick = onNewCategoryClick,
                  onNewExpenseClick = { onNewExpenseClick(null) },
                  onShowExpensesClick = {
                    scope.launch { scaffoldState.bottomSheetState.expand() }
                  },
                  onSettingsClick = onSettingsClick,
              )
            },
            scrollBehavior = scrollBehavior,
        )
      },
  ) { paddingValues ->
    Column(
        Modifier.fillMaxHeight(),
    ) {
      Column(
          Modifier.padding(Margins.compact),
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
        Crossfade(categories?.categories?.isNotEmpty() == true) {
          if (it) {
            Categories(
                onCategoryDeleteClicked = onCategoryDeleteClicked,
                categories = categories,
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
private fun NoExpenses(
    modifier: Modifier = Modifier,
) {
  Box(
      modifier = modifier.fillMaxWidth().fillMaxHeight(0.5F),
      contentAlignment = Alignment.Center,
  ) {
    Text(
        text = stringResource(R.string.no_expenses_title),
        style = MaterialTheme.typography.titleMedium,
    )
  }
}

@Composable
private fun Categories(
    modifier: Modifier = Modifier,
    bottomPadding: Dp,
    categories: Categories?,
    onCategoryDeleteClicked: (Category) -> Unit = {},
    onNewExpenseClick: (String?) -> Unit = {},
) {
  if (categories == null) {
    return
  }
  val scope = rememberCoroutineScope()
  LazyVerticalStaggeredGrid(
      columns = StaggeredGridCells.Adaptive(expenseCategoryCardWidth),
      verticalItemSpacing = IncrementalPaddings.x1,
      horizontalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
      modifier = modifier.fillMaxHeight(),
  ) {
    item(
        span = StaggeredGridItemSpan.FullLine,
    ) {
      Text(
          modifier = Modifier.semantics { heading() },
          text = stringResource(R.string.total_amount_title),
          style = MaterialTheme.typography.titleMedium,
      )
    }
    item {
      val totalAmount =
          remember(categories.totalAmount) { categories.totalAmount.toFormattedString() }
      Text(
          text = totalAmount,
          style = MaterialTheme.typography.bodyMedium,
      )
    }
    item(
        span = StaggeredGridItemSpan.FullLine,
    ) {
      Spacer(
          Modifier.height(IncrementalPaddings.x3),
      )
    }
    item(
        span = StaggeredGridItemSpan.FullLine,
    ) {
      Text(
          modifier = Modifier.semantics { heading() },
          text = stringResource(R.string.expenses_categories_title),
          style = MaterialTheme.typography.titleMedium,
      )
    }
    items(
        items = categories.categories,
        key = { category -> category.uuid },
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
          onDeleteClick = { onCategoryDeleteClicked(category) },
      )
    }

    item(
        span = StaggeredGridItemSpan.FullLine,
    ) {
      Spacer(
          Modifier.height(bottomPadding).animateContentSize(),
      )
    }
  }
}
