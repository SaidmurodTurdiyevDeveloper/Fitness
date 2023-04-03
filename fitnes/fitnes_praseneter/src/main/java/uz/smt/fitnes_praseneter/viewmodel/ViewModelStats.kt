package uz.smt.fitnes_praseneter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.smt.fitnes_praseneter.R
import uz.smt.fitnes_praseneter.model.DataStats
import uz.smt.fitnes_praseneter.model.StateStats
import uz.smt.fitnes_praseneter.model.ViewModelState
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/8/2023 3:53 PM for Fitnes.
 */
@HiltViewModel
class ViewModelStats @Inject constructor(

) : ViewModel() {
    private val _state: MutableState<ViewModelState<StateStats>> = mutableStateOf(
        ViewModelState(
            success = StateStats(
                tabList = listOf(
                    "Day", "Week", "Month"
                ), currentTab = 0,
                currentData = listOf(
                    DataStats(
                        id = "1",
                        name = "Hart Rate",
                        icon =  R.drawable.monitor_heart ,
                        stats = "124",
                        statsInfo = "bmp",
                        info = "80-120 Healthy",
                        color = Color(0xFFF46662)
                    ),
                    DataStats(
                        id = "2",
                        name = "Sleep",
                        icon = R.drawable.sleep,
                        stats = "8",
                        statsInfo = "h 42 m",
                        info = "Deep sleep 5h 13 m",
                        color = Color(0xFF7551F5)
                    )
                )
            )
        )
    )
    val state: State<ViewModelState<StateStats>> get() = _state

    private val _event = MutableSharedFlow<EventViewModel>()
    val event: SharedFlow<EventViewModel> get() = _event.asSharedFlow()

    fun onEvent(
        eventUi: EventUi
    ) {

        when (eventUi) {
            EventUi.Back -> {
                viewModelScope.launch {
                    _event.emit(EventViewModel.Exit)
                }
            }

            is EventUi.SelectItem -> {
                viewModelScope.launch {
                    _event.emit(EventViewModel.OpenStats(eventUi.id))
                }
            }
            is EventUi.SelectTab -> {
                viewModelScope.launch {
                    _state.value = state.value.successCopy {
                        //load new List
                        it?.copy(
                            currentTab = eventUi.index,
//                            currentData = newList()
                        )
                    }
                }
            }
        }
    }

    sealed class EventUi {
        object Back : EventUi()
        data class SelectTab(val index: Int) : EventUi()
        data class SelectItem(val id: String) : EventUi()
    }

    sealed class EventViewModel {
        object Exit : EventViewModel()
        data class OpenStats(val id: String) : EventViewModel()
    }
}