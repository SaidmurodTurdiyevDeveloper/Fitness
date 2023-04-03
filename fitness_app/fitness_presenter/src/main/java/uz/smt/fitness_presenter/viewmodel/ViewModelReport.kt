package uz.smt.fitness_presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import uz.smt.fitness_domen.model.DailyData
import uz.smt.fitness_domen.model.ExerciseWorkoutData
import uz.smt.fitness_domen.model.ReportWeek
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/29/2023 3:41 PM for Fitnes.
 */
@HiltViewModel
class ViewModelReport @Inject constructor() : ViewModel() {


    private val _state = mutableStateOf(StateViewModel())
    val state: State<StateViewModel> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()


    data class StateViewModel(
        val isLoading: Boolean = false,
        val error: String? = null,
        val dailyWorkout: DailyData = DailyData(
            value = "1",
            measure = "Workout"
        ),
        val dailyCalories: DailyData = DailyData(
            value = "3",
            measure = "Kcal"
        ),
        val dailyTime: DailyData = DailyData(
            value = "1",
            measure = "Minute"
        ),
        val reportWeek: ReportWeek = ReportWeek()
    )

    sealed class UIEvent {
        object SetAGoal : UIEvent()
        data class ItemClick(val id: String) : UIEvent()
    }

    sealed class ViewModelEvent {
        data class OpenItem(val id: String) : ViewModelEvent()
    }
}