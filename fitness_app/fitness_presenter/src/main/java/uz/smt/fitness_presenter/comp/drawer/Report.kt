package uz.smt.fitness_presenter.comp.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uz.smt.fitness_domen.model.DailyData
import uz.smt.fitness_domen.model.Report
import uz.smt.fitness_domen.model.Week
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow
import uz.smt.fitness_presenter.viewmodel.ViewModelReport

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 9:56 PM for Fitnes.
 */
@Composable
fun ReportDrawerScreen(
    navHostController: NavHostController,
    viewModel: ViewModelReport = hiltViewModel()
) {
    val state = viewModel.state.value
    Column {
        Divider(color = Color.LightGray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ValueAndMeasureText(data = state.dailyWorkout)
            ValueAndMeasureText(data = state.dailyCalories)
            ValueAndMeasureText(data = state.dailyTime)
        }
        Divider(color = Color.LightGray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Report".uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = DarkBlue
            )
            Text(
                text = "More".uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Yellow
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemReport(week = state.reportWeek.sunday)
            ItemReport(week = state.reportWeek.monday)
            ItemReport(week = state.reportWeek.tuesday)
            ItemReport(week = state.reportWeek.wednesday)
            ItemReport(week = state.reportWeek.thursday)
            ItemReport(week = state.reportWeek.friday)
            ItemReport(week = state.reportWeek.saturday)
        }
    }
}

@Composable
private fun ValueAndMeasureText(
    modifier: Modifier = Modifier,
    data: DailyData
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data.value,
            color = Yellow,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = data.measure,
            color = Color.LightGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ItemReport(
    week: Week,
    modifier: Modifier = Modifier
) {
    val width = when (week.reportType) {
        Report.Complete -> 8.dp
        Report.Current -> 0.dp
        Report.New -> 2.dp
    }
    Column(
        modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = week.firstCharacter.toString(),
            color = Color.LightGray,
            fontSize = 16.sp
        )
        if (week.reportType == Report.Current) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(
                        Yellow,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check Circle",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

        } else
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .border(
                        width = width,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
            )
        Text(
            text = week.day,
            color = Color.LightGray,
            fontSize = 16.sp
        )
    }
}