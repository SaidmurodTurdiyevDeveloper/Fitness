package uz.smt.fitness_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 8:14 PM for Fitnes.
 */
data class DiscoveryData(
    val name: String,
    val data: DataDiscovery
)

sealed class DataDiscovery {
    data class Exercise(val list: List<ExerciseDiscoverData>):DataDiscovery()
    data class Challenge(val list: List<ChallengeData>):DataDiscovery()
    data class Stretch(val list: List<StretchData>):DataDiscovery()
}
