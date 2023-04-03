package uz.smt.fitness_presenter.comp.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uz.smt.fitness_domen.model.ChallengeData
import uz.smt.fitness_domen.model.DataDiscovery
import uz.smt.fitness_domen.model.ExerciseDiscoverData
import uz.smt.fitness_domen.model.StretchData
import uz.smt.fitness_presenter.comp.designs.HorizontalGridItems
import uz.smt.fitness_presenter.comp.designs.ImageViewWithBackground
import uz.smt.fitness_presenter.ui.theme.Black_90
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow
import uz.smt.fitness_presenter.viewmodel.ViewModelDiscover

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 9:56 PM for Fitnes.
 */
@Composable
fun DiscoverDrawerScreen(
    navHostController: NavHostController,
    viewModel: ViewModelDiscover = hiltViewModel()
) {

    val state = viewModel.state.value

    Column() {
        Surface(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 56.dp),
            color = Yellow
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Only 4 movies for abs",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "4 simple exercise only Burn bully fat and firm your abs. Get a flat belly fast..",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.success) { data ->
                val name = data.name
                val ls = data.data
                println("First line")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = name, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    when (ls) {
                        is DataDiscovery.Challenge -> {
                            HorizontalGridItems(
                                items = ls.list, spanCount = 3,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) { item ->
                                println("Challenge line")

                                ItemChallenge(
                                    item = item,
                                    modifier = Modifier.padding(3.dp)
                                ) {
                                    viewModel.onEvent(ViewModelDiscover.UIEvent.ItemClick(item.id))
                                }
                            }
                        }
                        is DataDiscovery.Exercise -> {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                ls.list.forEach { item ->
                                    println("Exercise line")
                                    ItemExercise(
                                        item = item,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp)
                                    ) {
                                        viewModel.onEvent(ViewModelDiscover.UIEvent.ItemClick(item.id))
                                    }
                                }
                            }
                        }
                        is DataDiscovery.Stretch -> {
                            HorizontalGridItems(
                                items = ls.list, spanCount = 3,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) { item ->
                                println("Stretch line")

                                ItemStretch(
                                    item = item,
                                    modifier = Modifier.padding(3.dp)
                                ) {
                                    viewModel.onEvent(ViewModelDiscover.UIEvent.ItemClick(item.id))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemExercise(
    modifier: Modifier = Modifier,
    item: ExerciseDiscoverData,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = item.name, color = DarkBlue,
                        style = TextStyle(
                            color = DarkBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = item.name, color = DarkBlue,
                        style = TextStyle(
                            color = DarkBlue,
                            fontSize = 14.sp
                        ),
                        maxLines = 2
                    )
                }
                ImageViewWithBackground(
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight(),
                    image = item.image
                )
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }

    }
}

@Composable
fun ItemStretch(
    modifier: Modifier = Modifier,
    item: StretchData,
    onClick: () -> Unit
) {
    Column(modifier = modifier.clickable {
        onClick.invoke()
    }, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageViewWithBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            image = item.image,
            corner = 8.dp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = item.name, fontSize = 14.sp, color = DarkBlue, maxLines = 2, textAlign = TextAlign.Center)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemChallenge(
    modifier: Modifier = Modifier,
    item: ChallengeData,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .background(Yellow)
            .height(64.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.4f)
                    .background(Black_90)
            )
            Text(text = item.name, fontSize = 14.sp, color = DarkBlue, maxLines = 2)
        }
    }
}