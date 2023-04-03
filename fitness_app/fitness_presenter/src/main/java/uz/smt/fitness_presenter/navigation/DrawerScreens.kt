package uz.smt.fitness_presenter.navigation

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 4:18 PM for Fitnes.
 */
sealed class DrawerScreens(val route:String, val title:String=""){
    object HomeWorkout:DrawerScreens(route = "home_workout", title = "Home Workout")
    object Discover:DrawerScreens(route = "discover", title = "Discover")
    object Report:DrawerScreens(route = "report", title = "Report")
    object Reminder:DrawerScreens(route = "reminder", title = "Reminder")
    object Language:DrawerScreens(route = "language", title = "Language")
    object Setting:DrawerScreens(route = "setting", title = "Setting")
}
