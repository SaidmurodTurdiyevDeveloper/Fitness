package uz.smt.fitnes_praseneter.model

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/8/2023 3:57 PM for Fitnes.
 */
data class DataStats(
    val id: String,
    val name: String,
    @DrawableRes
    val icon: Int,
    val color: Color,
    val stats: String,
    val statsInfo: String,
    val info: String
)
