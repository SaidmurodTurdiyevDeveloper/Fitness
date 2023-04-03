@file:OptIn(ExperimentalMaterialApi::class)

package uz.smt.fitness_presenter.comp.designs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 1:37 PM for Fitnes.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    backgroundColor: Color = Color.White,
    paddingValues: PaddingValues = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
    corner: Dp = 32.dp,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(corner),
        color = backgroundColor,
        elevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = textStyle)
        }
    }
}

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(
        color = Yellow,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    color: Color = Yellow,
    borderWidth: Dp = 2.dp,
    paddingValues: PaddingValues =
        PaddingValues(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
    corner: Dp = 32.dp,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(corner),
        border = BorderStroke(
            width = borderWidth,
            color = color
        ),
        elevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = textStyle)
        }
    }
}