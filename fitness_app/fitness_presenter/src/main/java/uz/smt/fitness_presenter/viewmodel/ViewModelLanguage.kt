package uz.smt.fitness_presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.smt.fitness_domen.model.LanguageData
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/27/2023 5:56 PM for Fitnes.
 */
@HiltViewModel
class ViewModelLanguage @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(StateViewModel())
    val state: State<StateViewModel> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.ItemClick -> {
                viewModelScope.launch {
                    _state.value=state.value.copy(currentLanguageId = event.id)
                }
            }
        }
    }

    data class StateViewModel(
        val isLoading: Boolean = false,
        val error: String? = null,
        val languages: List<LanguageData> = emptyList(),
        val currentLanguageId: String? = null
    )

    sealed class UIEvent {
        data class ItemClick(val id: String) : UIEvent()
    }

    sealed class ViewModelEvent {
        data class OpenItem(val id: String) : ViewModelEvent()
    }
}