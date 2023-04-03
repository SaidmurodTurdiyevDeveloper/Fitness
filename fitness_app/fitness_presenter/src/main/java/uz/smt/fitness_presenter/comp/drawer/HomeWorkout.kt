package uz.smt.fitness_presenter.comp.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uz.smt.fitness_domen.model.DailyData
import uz.smt.fitness_domen.model.ExerciseWorkoutData
import uz.smt.fitness_presenter.comp.designs.ImageViewWithBackground
import uz.smt.fitness_presenter.comp.designs.RoundButton
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow
import uz.smt.fitness_presenter.viewmodel.ViewModelHomeWorkout

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 9:56 PM for Fitnes.
 */
@Composable
fun HomeWorkoutDrawerScreen(
    navHostController: NavHostController,
    viewModel: ViewModelHomeWorkout = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 0.5.dp),
            backgroundColor = Yellow
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ValueAndMeasureText(data = state.dailyWorkout)
                    ValueAndMeasureText(data = state.dailyCalories)
                    ValueAndMeasureText(data = state.dailyTime)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Home Workout", color = DarkBlue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Set weekly goals for a better body shape",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                RoundButton(text = "Set a Goal", modifier = Modifier.fillMaxWidth(0.7f).height(46.dp)) {
                    viewModel.onEvent(ViewModelHomeWorkout.UIEvent.SetAGoal)
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(state.exercises) { item ->
                ItemExercise(
                    item = item, onClick = {
                        viewModel.onEvent(ViewModelHomeWorkout.UIEvent.ItemClick(item.id))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(120.dp)
                )
            }
        }
    }

}

@Composable
private fun ValueAndMeasureText(
    modifier: Modifier = Modifier,
    data: DailyData,
    textStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = data.value, style = textStyle)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = data.measure, style = textStyle)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemExercise(
    modifier: Modifier = Modifier,
    item: ExerciseWorkoutData,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = item.name, color = DarkBlue,
                    style = TextStyle(
                        color = DarkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    maxLines = 1
                )
                if (item.timesExercise > 0 || item.maxCount > 0) {
                    Text(
                        text = item.timesExercise.toString() + " Exercise", color = DarkBlue,
                        style = TextStyle(
                            color = DarkBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.dailyCount.toString() + "/" + item.maxCount.toString(), color = DarkBlue,
                        style = TextStyle(
                            color = DarkBlue,
                            fontSize = 14.sp
                        ),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        progress = item.dailyCount.toFloat() / item.maxCount.toFloat(),
                        color = Yellow
                    )
                } else if (item.star) {
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                        Icon(imageVector = Icons.Outlined.Star, tint = Yellow, contentDescription = "Icon Star")
                        Icon(imageVector = Icons.Outlined.Star, tint = Yellow, contentDescription = "Icon Star")
                        Icon(imageVector = Icons.Outlined.Star, tint = Yellow, contentDescription = "Icon Star")
                    }
                }
            }
            ImageViewWithBackground(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),
                image = item.image
            )
        }
    }
}