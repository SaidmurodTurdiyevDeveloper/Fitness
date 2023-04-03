package uz.smt.fitnes.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitnes.navigator.HomeNavHost
import uz.smt.fitnes.ui.theme.FitnessTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var provider: Navigator.Provider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessTheme {
                HomeNavHost(activity = this, provider = provider)
            }
        }
    }
}
