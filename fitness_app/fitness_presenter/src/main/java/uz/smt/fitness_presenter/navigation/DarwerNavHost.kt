package uz.smt.fitness_presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.smt.fitness_presenter.comp.drawer.*

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 4:34 PM for Fitnes.
 */
@Composable
fun DrawerNavHost(
    modifier: Modifier=Modifier,
    drawerNavHost: NavHostController,
    navHostController: NavHostController,
    onTitleChange:(String)->Unit
) {
    NavHost(modifier=modifier,navController = drawerNavHost, startDestination =DrawerScreens.HomeWorkout.route ){
        composable(DrawerScreens.HomeWorkout.route){
            onTitleChange(DrawerScreens.HomeWorkout.title)
            HomeWorkoutDrawerScreen(navHostController)
        }
        composable(DrawerScreens.Discover.route){
            onTitleChange(DrawerScreens.Discover.title)
            DiscoverDrawerScreen(navHostController)
        }
        composable(DrawerScreens.Language.route){
            onTitleChange(DrawerScreens.Language.title)
            LanguageOptionsDrawerScreen(navHostController)
        }
        composable(DrawerScreens.Reminder.route){
            onTitleChange(DrawerScreens.Reminder.title)
            ReminderDrawerScreen(navHostController)
        }
        composable(DrawerScreens.Report.route){
            onTitleChange(DrawerScreens.Report.title)
            ReportDrawerScreen(navHostController)
        }
        composable(DrawerScreens.Setting.route){
            onTitleChange(DrawerScreens.Setting.title)
            SettingDrawerScreen()
        }
    }
}