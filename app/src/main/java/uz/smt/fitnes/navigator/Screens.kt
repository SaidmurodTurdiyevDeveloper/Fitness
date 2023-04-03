package uz.smt.fitnes.navigator

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:33 PM for Fitnes.
 */
sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object StartingScreen : Screens("starting_screen")
}
