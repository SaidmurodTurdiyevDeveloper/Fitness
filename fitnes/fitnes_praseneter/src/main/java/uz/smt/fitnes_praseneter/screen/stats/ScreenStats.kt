package uz.smt.fitnes_praseneter.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uz.smt.fitnes_praseneter.screen.stats.ItemViewStats
import uz.smt.fitnes_praseneter.utils.TabLayout
import uz.smt.fitnes_praseneter.utils.ToolBar
import uz.smt.fitnes_praseneter.utils.ViewModelUniversalScreen
import uz.smt.fitnes_praseneter.viewmodel.ViewModelStats

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/8/2023 3:52 PM for Fitnes.
 */
@Composable
fun ScreenStats(
    navHostController: NavHostController,
    viewModel: ViewModelStats = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ToolBar(title = "Stats", iconClick = {
                viewModel.onEvent(ViewModelStats.EventUi.Back)
            })
        }
    ) { innerPadding ->
        ViewModelUniversalScreen(state = viewModel.state.value, modifier = Modifier.padding(innerPadding)) {state->
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(24.dp))
                TabLayout(
                    modifier = Modifier.fillMaxWidth(0.8f).height(36.dp),
                    list = state.tabList,
                    selected = state.currentTab,
                ) { tabIndex ->
                    viewModel.onEvent(ViewModelStats.EventUi.SelectTab(tabIndex))
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp,), modifier = Modifier.fillMaxHeight()
                ) {
                    items(state.currentData) { data ->
                        ItemViewStats(item = data,
                            onClick = { id ->
                                viewModel.onEvent(ViewModelStats.EventUi.SelectItem(id))
                            })
                    }
                }
            }
        }
    }
}