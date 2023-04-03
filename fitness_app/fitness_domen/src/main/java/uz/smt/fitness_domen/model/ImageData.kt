package uz.smt.fitness_domen.model

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 1:14 PM for Fitnes.
 */
data class ImageData(
    @DrawableRes
    val imageRes: Int? = null,
    val imageNetworkUrl: String? = null,
    val backGroundColor:BackgroundColor=BackgroundColor.Yellow
)
enum class BackgroundColor{
    Yellow,Red
}
