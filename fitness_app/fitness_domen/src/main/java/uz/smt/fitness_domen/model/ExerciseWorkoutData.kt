package uz.smt.fitness_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 1:07 PM for Fitnes.
 */
data class ExerciseWorkoutData(
    val id:String,
    val name: String,
    val image: ImageData,
    val timesExercise: Int = 0,
    val dailyCount: Int = 0,
    val maxCount: Int = 0,
    val star: Boolean = false
)
