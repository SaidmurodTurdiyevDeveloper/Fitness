package uz.smt.fitness_presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.smt.fitness_domen.model.BackgroundColor
import uz.smt.fitness_domen.model.DailyData
import uz.smt.fitness_domen.model.ExerciseWorkoutData
import uz.smt.fitness_domen.model.ImageData
import uz.smt.fitness_presenter.R
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/24/2023 8:51 PM for Fitnes.
 */
@HiltViewModel
class ViewModelHomeWorkout @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(StateViewModel())
    val state: State<StateViewModel> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    init {
        val list = listOf(
            ExerciseWorkoutData(
                id = "1",
                name = "Ful body",
                image = ImageData(imageRes = R.drawable.training_men),
                timesExercise = 16,
                dailyCount = 23,
                maxCount = 24
            ),
            ExerciseWorkoutData(
                id = "2",
                name = "Best Quarantine",
                image = ImageData(imageRes = R.drawable.training_women, backGroundColor = BackgroundColor.Red),
                timesExercise = 20,
                dailyCount = 10,
                maxCount = 22
            ),
            ExerciseWorkoutData(
                id = "3",
                name = "Chest Beginner",
                image = ImageData(imageRes = R.drawable.training_men_two),
                star = true
            )
        )

        _state.value = state.value.copy(
            dailyWorkout = DailyData(
                value = "1",
                measure = "Workout"
            ),
            dailyCalories = DailyData(
                value = "6",
                measure = "Kcal"
            ),
            dailyTime = DailyData(
                value = "0",
                measure = "Minutes"
            ), exercises = list
        )
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.ItemClick -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenItem(event.id))
                }
            }
            UIEvent.SetAGoal -> {

            }
        }
    }

    data class StateViewModel(
        val isLoading: Boolean = false,
        val error: String? = null,
        val dailyWorkout: DailyData = DailyData(value = "", measure = ""),
        val dailyCalories: DailyData = DailyData(value = "", measure = ""),
        val dailyTime: DailyData = DailyData(value = "", measure = ""),
        val exercises: List<ExerciseWorkoutData> = emptyList()
    )

    sealed class UIEvent {
        object SetAGoal : UIEvent()
        data class ItemClick(val id: String) : UIEvent()
    }

    sealed class ViewModelEvent {
        data class OpenItem(val id: String) : ViewModelEvent()
    }
}