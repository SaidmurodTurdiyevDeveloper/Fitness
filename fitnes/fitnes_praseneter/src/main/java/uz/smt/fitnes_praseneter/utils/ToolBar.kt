package uz.smt.fitnes_praseneter.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.smt.fitnes_praseneter.ui.theme.SelectColor
import uz.smt.fitnes_praseneter.ui.theme.UnSelectColor

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:43 AM for Fitnes.
 */
@Composable
fun ToolBar(
    title: String,
    icon: ImageVector = Icons.Default.KeyboardArrowLeft,
    iconClick: () -> Unit,
    color: Color = SelectColor,
    titleStyle: TextStyle = TextStyle(
        color = SelectColor,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
    )
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            modifier = Modifier
                .size(36.dp)
                ,
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.clickable {
                    iconClick.invoke()
                }.background(
                    shape = CircleShape,
                    color = UnSelectColor
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    tint = color,
                    contentDescription = "Icon ToolBar"
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = title, style = titleStyle)
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(52.dp))
    }
}
