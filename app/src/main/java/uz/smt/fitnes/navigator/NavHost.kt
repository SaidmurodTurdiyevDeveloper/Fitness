package uz.smt.fitnes.navigator

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitnes.presenter.comp.ScreenSplash
import uz.smt.fitnes.presenter.comp.ScreenStarting

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:52 PM for Fitnes.
 */
@Composable
fun HomeNavHost(activity: Activity, provider: Navigator.Provider) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route)
    {
        composable(route = Screens.SplashScreen.route) {
            ScreenSplash(
                navController = navController,
                activity = activity,
                provider = provider
            )
        }
        composable(route = Screens.StartingScreen.route) {
            ScreenStarting(
                navController = navController,
                activity = activity,
                provider = provider
            )
        }
    }
}