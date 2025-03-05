package zolnaczpiotr8.com.github.expenses.log.feature.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import zolnaczpiotr8.com.github.expenses.log.core.data.repository.SettingsRepository
import javax.inject.Inject

private const val STOP_SHARING_COROUTINE_DELAY = 5_000L

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    val showEmptyCategories: StateFlow<Boolean> = settingsRepository
        .settings
        .map {
            it.showEmptyCategories
        }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SHARING_COROUTINE_DELAY),
            initialValue = false,
        )

    fun setShowEmptyCategories(
        show: Boolean,
    ) {
        viewModelScope.launch {
            settingsRepository.setShowEmptyCategories(show)
        }
    }
}
