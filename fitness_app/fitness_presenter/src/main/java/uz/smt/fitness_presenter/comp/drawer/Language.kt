package uz.smt.fitness_presenter.comp.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uz.smt.fitness_domen.model.LanguageData
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.LightGray
import uz.smt.fitness_presenter.viewmodel.ViewModelLanguage

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 9:57 PM for Fitnes.
 */
@Composable
fun LanguageOptionsDrawerScreen(
    navHostController: NavHostController,
    viewModel: ViewModelLanguage = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.languages) { item ->
            ItemLanguage(
                item = item,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                isSelectedLanguage = state.currentLanguageId == item.id
            ) {
                viewModel.onEvent(ViewModelLanguage.UIEvent.ItemClick(item.id))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemLanguage(
    item: LanguageData,
    modifier: Modifier,
    isSelectedLanguage: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        color = LightGray
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isSelectedLanguage)
                    Icons.Default.CheckCircle
                else
                    Icons.Default.Check,
                contentDescription = "Selected icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = item.name, color = DarkBlue, fontSize = 16.sp)
        }
    }
}