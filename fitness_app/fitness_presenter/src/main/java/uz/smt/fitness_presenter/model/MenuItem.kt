package uz.smt.fitness_presenter.model

import androidx.compose.ui.graphics.vector.ImageVector
import uz.smt.fitness_presenter.navigation.DrawerScreens

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/19/2023 1:50 PM for Fitnes.
 */
data class MenuItem(
    val route: DrawerScreens?,
    val title: String,
    val icon: ImageVector
)
