package zolnaczpiotr8.com.github.expenses.log.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.model.Categories
import zolnaczpiotr8.com.github.expenses.log.core.model.Category
import zolnaczpiotr8.com.github.expenses.log.core.model.Expense
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.components.IndeterminateLinearIndicator
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.IncrementalPaddings
import zolnaczpiotr8.com.github.expenses.log.core.ui.material.design3.spacing.Margins
import zolnaczpiotr8.com.github.expenses.log.feature.home.R
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.category.CategoryCard
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.category.expenseCategoryCardWidth
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.delete.DeleteDialog
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.expense.ExpenseListItem
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.IsNotEmptyFilterChip
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilter
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilterChip
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilterModalBottomSheet
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.DateFilterPickerDialog
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.filter.chips.date.rememberDateFilterSheetState
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.category.CategoryMenuModalBottomSheet
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.category.rememberCategoryMenuSheetState
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.expense.ExpenseMenuModalBottomSheet
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.expense.rememberExpenseMenuSheetState
import zolnaczpiotr8.com.github.expenses.log.feature.home.ui.menu.main.MainMenuModalBottomSheet
import kotlin.uuid.ExperimentalUuidApi
import zolnaczpiotr8.com.github.expenses.log.core.ui.R as coreUiR

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewExpenseClick: () -> Unit = {
    },
    onSettingsClick: () -> Unit = {
    },
) {
    val isLoading by viewModel.isLoading
        .collectAsStateWithLifecycle()
    val expenses by viewModel.expenses
        .collectAsStateWithLifecycle()
    val categories by viewModel.categories
        .collectAsStateWithLifecycle()
    HomeScreen(
        isLoading = isLoading,
        categories = categories,
        expenses = expenses,
        onCategoryDeleteClicked = viewModel::onCategoryDeleteClicked,
        onExpenseDeleteClicked = viewModel::onExpenseDeleteClicked,
        onNewExpenseClicked = onNewExpenseClick,
        onSettingsClicked = onSettingsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
internal fun HomeScreen(
    isLoading: Boolean = false,
    categories: Categories,
    expenses: ImmutableList<Expense> = persistentListOf(),
    onExpenseDeleteClicked: (Expense) -> Unit = {
    },
    onCategoryDeleteClicked: (Category) -> Unit = {
    },
    onNewExpenseClicked: () -> Unit = {
    },
    onSettingsClicked: () -> Unit = {
    },
) {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        sheetContent = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
                modifier = Modifier
                    .semantics {
                        collectionInfo = CollectionInfo(
                            rowCount = expenses.size,
                            columnCount = 1,
                        )
                    },
            ) {
                itemsIndexed(
                    items = expenses,
                    key = { _, expense ->
                        expense.uuid.toHexString()
                    },
                ) { index, expense ->
                    val menuState = rememberExpenseMenuSheetState(expense.title)
                    ExpenseListItem(
                        modifier = Modifier.semantics {
                            collectionItemInfo = CollectionItemInfo(
                                rowIndex = index,
                                rowSpan = 1,
                                columnSpan = 1,
                                columnIndex = 0,
                            )
                        },
                        expense = expense,
                        onMenuClick = {
                            scope.launch {
                                menuState.show()
                            }
                        },
                    )

                    var isDeleteDialogVisible by rememberSaveable {
                        mutableStateOf(false)
                    }
                    ExpenseMenuModalBottomSheet(
                        state = menuState,
                        onEditClick = {
                        },
                        onDeleteClick = {
                            isDeleteDialogVisible = true
                        },
                    )

                    DeleteDialog(
                        isVisible = isDeleteDialogVisible,
                        onHide = {
                            isDeleteDialogVisible = false
                        },
                        text = stringResource(R.string.delete_expense_text),
                        onDeleteClick = {
                            onExpenseDeleteClicked(expense)
                        },
                    )
                }

                item {
                    Spacer(
                        Modifier.windowInsetsBottomHeight(WindowInsets.systemBars)
                            .animateContentSize(),
                    )
                }
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val applicationName = stringResource(coreUiR.string.application_label)
                    Text(
                        text = applicationName,
                        modifier = Modifier.clearAndSetSemantics {
                            testTag = applicationName
                        },
                    )
                },
                actions = {
                    val sheetState = rememberModalBottomSheetState()

                    MenuIconButton {
                        scope.launch {
                            sheetState.show()
                        }
                    }

                    MainMenuModalBottomSheet(
                        state = sheetState,
                        onNewExpenseClick = onNewExpenseClicked,
                        expensesVisible = scaffoldState.bottomSheetState.hasExpandedState and categories.isNotEmpty(),
                        onShowExpensesClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        },
                        onHideExpensesClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        },
                        onNewCategoryClick = {
                        },
                        onSettingsClick = onSettingsClicked,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxHeight(),
        ) {
            AnimatedVisibility(isLoading) {
                IndeterminateLinearIndicator(
                    stringResource(R.string.expenses_and_categories_progress_bar_label),
                )
            }
            Spacer(Modifier.height(IncrementalPaddings.x1))
            Column(
                Modifier
                    .padding(Margins.compact)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(
                    IncrementalPaddings.x1,
                ),
            ) {
                Text(
                    modifier = Modifier.semantics {
                        heading()
                    },
                    text = stringResource(R.string.filters_title),
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        IncrementalPaddings.x1,
                    ),
                ) {
                    val filter: DateFilter = DateFilter.Month
                    val sheetState = rememberDateFilterSheetState(filter)
                    DateFilterChip(
                        filter = filter,
                        onClick = {
                            scope.launch {
                                sheetState.show()
                            }
                        },
                    )

                    var datePickerDialogVisible by rememberSaveable {
                        mutableStateOf(false)
                    }

                    DateFilterModalBottomSheet(
                        state = sheetState,
                        onYearClicked = {
                        },
                        onQuarterClicked = {
                        },
                        onAnyDateClicked = {
                        },
                        onMonthClicked = {
                        },
                        onCustomClicked = {
                            datePickerDialogVisible = true
                        },
                    )

                    if (datePickerDialogVisible) {
                        DateFilterPickerDialog(
                            initial = filter as? DateFilter.Custom,
                            onDismiss = {
                                datePickerDialogVisible = false
                            },
                            onPicked = {
                                datePickerDialogVisible = false
                            },
                        )
                    }

                    IsNotEmptyFilterChip(
                        onClick = {
                        },
                    )
                }
                Spacer(
                    Modifier.height(IncrementalPaddings.x3),
                )
                Crossfade(categories.isEmpty()) { noExpenses ->
                    if (noExpenses) {
                        NoExpenses()
                    } else {
                        Categories(
                            onCategoryDeleteClicked = onCategoryDeleteClicked,
                            categories = categories,
                            bottomPadding = paddingValues.calculateBottomPadding(),
                        )
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
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5F),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.no_expenses_title),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
private fun Categories(
    modifier: Modifier = Modifier,
    bottomPadding: Dp,
    categories: Categories,
    onCategoryDeleteClicked: (Category) -> Unit = {
    },
) {
    val scope = rememberCoroutineScope()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(expenseCategoryCardWidth),
        verticalItemSpacing = IncrementalPaddings.x1,
        horizontalArrangement = Arrangement.spacedBy(IncrementalPaddings.x1),
        modifier = modifier
            .fillMaxHeight(),
    ) {
        item(
            span = StaggeredGridItemSpan.FullLine,
        ) {
            Text(
                modifier = Modifier.semantics {
                    heading()
                },
                text = stringResource(R.string.total_amount_title),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        item {
            Text(
                text = categories.totalAmount.toString(),
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
                text = stringResource(R.string.expenses_categories_title),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        items(
            items = categories.categories,
            key = { category ->
                category.uuid.toHexString()
            },
        ) { category ->
            val menuState = rememberCategoryMenuSheetState(category.title)
            CategoryCard(
                modifier = Modifier.animateItem(),
                category = category,
                onMenuClick = {
                    scope.launch {
                        menuState.show()
                    }
                },
            )

            var isDeleteDialogVisible by rememberSaveable {
                mutableStateOf(false)
            }
            CategoryMenuModalBottomSheet(
                state = menuState,
                onNewExpenseClick = {
                },
                onEditClick = {
                },
                onDeleteClick = {
                    isDeleteDialogVisible = true
                },
            )

            DeleteDialog(
                isVisible = isDeleteDialogVisible,
                onHide = {
                    isDeleteDialogVisible = false
                },
                text = stringResource(R.string.delete_expense_category_text),
                onDeleteClick = {
                    onCategoryDeleteClicked(category)
                },
            )
        }

        item(
            span = StaggeredGridItemSpan.FullLine,
        ) {
            Spacer(
                Modifier.height(bottomPadding)
                    .animateContentSize(),
            )
        }
    }
}
