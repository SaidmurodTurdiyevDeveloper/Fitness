package uz.smt.fitnes.presenter.comp

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import uz.smt.common_utils.activity.Activities
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitnes.presenter.viewModel.ViewModelStarting
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:54 PM for Fitnes.
 */
@Composable
fun ScreenStarting(
    navController: NavHostController,
    provider: Navigator.Provider,
    activity: Activity,
    viewModel: ViewModelStarting = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                ViewModelStarting.ViewModelEvent.LoadFacebookAccount -> {

                }
                ViewModelStarting.ViewModelEvent.LoadGoogleAccount -> {

                }
                ViewModelStarting.ViewModelEvent.OpenHome -> {
                    runCatching {
                        provider.getActivities(Activities.FitnessActivity).navigate(activity)
                    }.onSuccess {
                        delay(200)
                        activity.finish()
                    }
                }
                ViewModelStarting.ViewModelEvent.OpenLoginPassword -> {

                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = uz.smt.fitnes.R.drawable.traning_pulse),
                contentDescription = "Background image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.4f)
                    .background(Color.Black)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.isOpenSignUp) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp)
                        ) {
                            IconButton(onClick = {
                                viewModel.onEvent(ViewModelStarting.UiEvent.Close)
                            }) {
                                Icon(imageVector = Icons.Default.Close, tint = Color.White, contentDescription = "Close Sign up screen")
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 32.dp, bottom = 16.dp)
                            ) {
                                Text(text = "Log in to your account", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkBlue)
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {

                                    },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Yellow
                                    ), modifier = Modifier.width(250.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Favorite, tint = Color.White, contentDescription = "google")
                                    Text(
                                        text = "Continue with google",
                                        maxLines = 1,
                                        color = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {

                                    },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Yellow
                                    ), modifier = Modifier.width(250.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Favorite, tint = Color.White, contentDescription = "google")
                                    Text(
                                        text = "Continue with Facebook",
                                        maxLines = 1,
                                        color = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {

                                    },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Yellow
                                    ), modifier = Modifier.width(250.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Favorite, tint = Color.White, contentDescription = "google")
                                    Text(
                                        text = "Continue with Password",
                                        maxLines = 1,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                } else {
                    TextButton(
                        onClick = { viewModel.onEvent(ViewModelStarting.UiEvent.SignUp) },
                        shape = RoundedCornerShape(32.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow)
                    ) {
                        Text(
                            text = "Sign up",
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 28.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have account?",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign in",
                        color = Yellow,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(ViewModelStarting.UiEvent.SignIn)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}