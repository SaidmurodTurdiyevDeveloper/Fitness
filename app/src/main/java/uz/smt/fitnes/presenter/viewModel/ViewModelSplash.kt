package uz.smt.fitnes.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.smt.common_utils.data.shared.SharedDatabase
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:07 PM for Fitnes.
 */
@HiltViewModel
class ViewModelSplash @Inject constructor(
    private val store: SharedDatabase
) : ViewModel() {

    private val _event = Channel<EventViewModel>()
    val event = _event.receiveAsFlow()

    init {
        if (store.firstEnter) {
            viewModelScope.launch {
                delay(2000)
                _event.send(EventViewModel.LaunchHomeScreen)
            }
        } else {
            viewModelScope.launch {
                delay(2000)
                _event.send(EventViewModel.OpenStartingScreen)
            }
        }
    }

    sealed class EventViewModel() {
        object OpenStartingScreen : EventViewModel()
        object LaunchHomeScreen : EventViewModel()
    }
}