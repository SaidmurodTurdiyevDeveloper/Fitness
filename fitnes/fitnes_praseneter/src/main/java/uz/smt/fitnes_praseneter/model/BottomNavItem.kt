package uz.smt.fitnes_praseneter.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:14 PM for Fitnes.
 */
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
