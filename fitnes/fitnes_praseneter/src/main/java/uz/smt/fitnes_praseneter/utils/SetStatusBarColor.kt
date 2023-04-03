package uz.smt.fitnes_praseneter.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:40 PM for Fitnes.
 */
@Composable
fun SetStatusBarColor(
    systemUiController: SystemUiController = rememberSystemUiController(),
    lightThemeColor: Color = Color.White,
    darkThemeColor: Color = Color.Black
) {
    if (isSystemInDarkTheme()) {
        systemUiController.setSystemBarsColor(
            color = darkThemeColor
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = lightThemeColor
        )
    }
}