package uz.smt.fitnes.presenter.comp

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import uz.smt.common_utils.activity.Activities
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitnes.R
import uz.smt.fitnes.presenter.viewModel.ViewModelSplash
import uz.smt.fitnes.navigator.Screens

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 12:24 PM for Fitnes.
 */

@Composable
fun ScreenSplash(
    navController: NavHostController,
    provider: Navigator.Provider,
    activity: Activity,
    viewModel: ViewModelSplash = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                ViewModelSplash.EventViewModel.LaunchHomeScreen -> {
                    runCatching {
                        provider.getActivities(Activities.FitnessActivity).navigate(activity)
                    }.onSuccess {
                        delay(200)
                        activity.finish()
                    }

                }
                ViewModelSplash.EventViewModel.OpenStartingScreen -> {
                    navController.navigate(Screens.StartingScreen.route)
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val loading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fitness_loading))
        LottieAnimation(composition = loading, iterations = Int.MAX_VALUE)
    }
}