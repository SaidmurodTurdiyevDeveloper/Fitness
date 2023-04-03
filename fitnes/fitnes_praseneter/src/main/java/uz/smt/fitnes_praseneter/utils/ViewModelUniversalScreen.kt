package uz.smt.fitnes_praseneter.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.smt.fitnes_praseneter.model.ViewModelState

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:24 PM for Fitnes.
 */
@Composable
fun <T> ViewModelUniversalScreen(
    modifier: Modifier = Modifier,
    state: ViewModelState<T>,
    successScreen: @Composable (T) -> Unit
) {
    when {
        state.loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return
        }
        state.error != null && state.error.isNotEmpty() -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            return
        }
        state.success != null -> {
            successScreen.invoke(state.success)
            return
        }
        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something is Wrong",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}