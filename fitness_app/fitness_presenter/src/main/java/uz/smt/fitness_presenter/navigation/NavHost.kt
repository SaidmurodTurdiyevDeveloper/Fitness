package uz.smt.fitness_presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.smt.fitness_presenter.comp.HomeScreen

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 4:28 PM for Fitnes.
 */
@Composable
fun MainNavHost(
    drawerNavHostController: NavHostController,
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                drawerNavHostController = drawerNavHostController,
                navHostController = navHostController
            )
        }
    }
}