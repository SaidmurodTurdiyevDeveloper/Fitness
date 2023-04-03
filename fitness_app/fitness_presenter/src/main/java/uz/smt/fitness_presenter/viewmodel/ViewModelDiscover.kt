package uz.smt.fitness_presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.smt.fitness_domen.model.*
import uz.smt.fitness_presenter.R
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/24/2023 8:51 PM for Fitnes.
 */
@HiltViewModel
class ViewModelDiscover @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(StateViewModel())
    val state: State<StateViewModel> get() = _state

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    init {

        val listExercise = listOf(
            ExerciseDiscoverData(
                id = "0",
                name = "Belly fat burner Hit",
                image = ImageData(imageRes = R.drawable.training_men, backGroundColor = BackgroundColor.Yellow),
                info = "14 min. Beginner"
            ),
            ExerciseDiscoverData(
                id = "1",
                name = "Lose fat (No Jumping)",
                image = ImageData(imageRes = R.drawable.training_women, backGroundColor = BackgroundColor.Yellow),
                info = "14 min. Intermediate"
            )
        )

        val listStretch = listOf(
            StretchData(
                id = "0",
                name = "Full body stretching",
                image = ImageData(imageRes = R.drawable.training_women_two, backGroundColor = BackgroundColor.Yellow),
            ),
            StretchData(
                id = "1",
                name = "Warm up",
                image = ImageData(imageRes = R.drawable.training_men_vs_women, backGroundColor = BackgroundColor.Yellow)
            ),
            StretchData(
                id = "2",
                name = "Lower body stretching",
                image = ImageData(imageRes = R.drawable.training_men_vs_women_two, backGroundColor = BackgroundColor.Yellow)
            ),
            StretchData(
                id = "3",
                name = "Sleeping time stretch",
                image = ImageData(imageRes = R.drawable.training_women, backGroundColor = BackgroundColor.Red)
            )
        )

        val list = listOf(
            DiscoveryData(
                name = "Pic For You",
                data = DataDiscovery.Exercise(
                    list = listExercise
                )
            ),
            DiscoveryData(
                name = "Stretch",
                data = DataDiscovery.Stretch(list = listStretch)
            )
        )

        _state.value = state.value.copy(success = list)
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.ItemClick -> {
                viewModelScope.launch {
                    _event.emit(ViewModelEvent.OpenItem(event.id))
                }
            }
        }
    }

    data class StateViewModel(
        val isLoading: Boolean = false,
        val error: String? = null,
        val success: List<DiscoveryData> = emptyList()
    )

    sealed class UIEvent {
        data class ItemClick(val id: String) : UIEvent()
    }

    sealed class ViewModelEvent {
        data class OpenItem(val id: String) : ViewModelEvent()
    }
}