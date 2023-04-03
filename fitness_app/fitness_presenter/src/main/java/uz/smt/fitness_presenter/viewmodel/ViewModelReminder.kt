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
import uz.smt.fitness_domen.model.ReminderData
import uz.smt.fitness_domen.model.TimeData
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/27/2023 5:56 PM for Fitnes.
 */
@HiltViewModel
class ViewModelReminder @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(StateViewModel())
    val state: State<StateViewModel> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    init {
        val list = listOf(
            ReminderData(
                id = "1",
                isRemindActive = true,
                isRemindOnMonday = true,
                isRemindOnTuesday = false,
                isRemindOnWednesday = true,
                isRemindOnThursday = true,
                isRemindOnFriday = false,
                isRemindOnSunday = true,
                isRemindOnSaturday = false,
                time = TimeData(minute = 30, clock = 16, text = "16:30")
            )
        )
        _state.value = state.value.copy(
            reminderList = list
        )
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.AddNewItem -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenBottomSheet(data = null))
                }
            }
            is UIEvent.ItemClick -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenBottomSheet(data = event.data))
                }
            }

            is UIEvent.ItemDelete -> {
                viewModelScope.launch {
                    //i must delete
                }
            }
            is UIEvent.SaveData -> {
                viewModelScope.launch {
                    //i must update
                }
            }
            is UIEvent.ChangeIsActive -> {
                viewModelScope.launch {
                    //
                }
            }
        }
    }

    data class StateViewModel(
        val isLoading: Boolean = false,
        val error: String? = null,
        val reminderList: List<ReminderData> = emptyList(),
    )

    sealed class UIEvent {
        object AddNewItem : UIEvent()
        data class ItemClick(val data: ReminderData) : UIEvent()
        data class ItemDelete(val id: String) : UIEvent()
        data class SaveData(val data: ReminderData) : UIEvent()
        data class ChangeIsActive(val data: ReminderData, val isActive: Boolean) : UIEvent()
    }

    sealed class ViewModelEvent {
        data class OpenBottomSheet(val data: ReminderData? = null) : ViewModelEvent()
    }
}