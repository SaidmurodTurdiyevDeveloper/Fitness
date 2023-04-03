package uz.smt.fitness_presenter.navigation

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 4:41 PM for Fitnes.
 */
sealed class Screens(val route:String){
    object HomeScreen:Screens("home_screen")
}
