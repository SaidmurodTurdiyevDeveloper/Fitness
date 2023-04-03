package uz.smt.fitness_presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitness_presenter.navigation.MainNavHost
import uz.smt.fitness_presenter.ui.theme.FitnesTheme

@AndroidEntryPoint
class ActivityFitness : ComponentActivity() {
    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, ActivityFitness::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val drawerNavHostController = rememberNavController()
            FitnesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainNavHost(
                        navHostController = navHostController,
                        drawerNavHostController = drawerNavHostController
                    )
                }
            }
        }
    }
}

object GoToFitnessActivity : Navigator {
    override fun navigate(activity: Activity) {
        ActivityFitness.launchActivity(activity)
    }
}