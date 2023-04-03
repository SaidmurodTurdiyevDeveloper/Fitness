package uz.smt.fitnes.presenter.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.smt.common_utils.data.shared.SharedDatabase
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/15/2023 1:47 PM for Fitnes.
 */
@HiltViewModel
class ViewModelStarting @Inject constructor(
    private val store: SharedDatabase
) : ViewModel() {
    private val _state = mutableStateOf(StateUi())
    val state: State<StateUi> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    data class StateUi(
        val isOpenSignUp: Boolean = false
    )

    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.FaceBook -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.LoadFacebookAccount)
                }
            }
            UiEvent.Google -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.LoadGoogleAccount)
                }
            }
            UiEvent.LoginPassword -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenLoginPassword)
                }
            }
            UiEvent.SignIn -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenHome)
                }
            }
            UiEvent.SignUp -> {
                _state.value = state.value.copy(isOpenSignUp = true)
            }
            UiEvent.Close -> {
                _state.value = state.value.copy(isOpenSignUp = false)
            }
        }
    }

    sealed class UiEvent {
        object SignUp : UiEvent()
        object Close : UiEvent()
        object SignIn : UiEvent()
        object Google : UiEvent()
        object FaceBook : UiEvent()
        object LoginPassword : UiEvent()
    }

    sealed class ViewModelEvent {
        object OpenHome : ViewModelEvent()
        object LoadGoogleAccount : ViewModelEvent()
        object LoadFacebookAccount : ViewModelEvent()
        object OpenLoginPassword : ViewModelEvent()
    }
}