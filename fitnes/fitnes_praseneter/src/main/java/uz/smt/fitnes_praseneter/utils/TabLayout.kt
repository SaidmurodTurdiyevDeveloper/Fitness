package uz.smt.fitnes_praseneter.utils

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import uz.smt.fitnes_praseneter.ui.theme.ColorAccent
import uz.smt.fitnes_praseneter.ui.theme.SelectColor
import uz.smt.fitnes_praseneter.ui.theme.UnSelectColor

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 10:17 PM for Fitnes.
 */

@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    list: List<String>,
    selected: Int = 0,
    onItemClick: (Int) -> Unit
) {
    var isRight by remember {
        mutableStateOf(false)
    }
    TabRow(
        selectedTabIndex = selected,
        tabs = {
            list.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .zIndex(2f)
                        .clickable {
                            isRight = index > selected
                            onItemClick.invoke(
                                index
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        fontWeight = FontWeight.Bold,
                        color = if (selected == index)
                            Color.White
                        else
                            ColorAccent
                    )
                }
            }
        },
        indicator = {
            TabIndicator(
                tabPosition = it,
                index = selected,
                isRight
            )
        },
        backgroundColor = Color.Transparent,
        modifier = modifier
            .fillMaxSize()
            .background(
                shape = RoundedCornerShape(12.dp),
                color = UnSelectColor
            )
    )
}

@Composable
private fun TabIndicator(
    tabPosition: List<TabPosition>,
    index: Int,
    isRight: Boolean
) {
    val transition = updateTransition(targetState = index, label = "")
    val leftIndicator by transition.animateDp(
        label = "", transitionSpec = {
            spring(
                stiffness = if (isRight)
                    Spring.StiffnessVeryLow
                else
                    Spring.StiffnessMedium
            )
        }
    ) {
        tabPosition[it].left
    }
    val rightIndicator by transition.animateDp(
        label = "", transitionSpec = {
            spring(
                stiffness = if (isRight)
                    Spring.StiffnessMedium
                else
                    Spring.StiffnessVeryLow
            )
        }
    ) {
        tabPosition[it].right
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = leftIndicator)
            .zIndex(1f)
            .width(rightIndicator - leftIndicator)
            .fillMaxSize()
            .background(color = SelectColor, shape = RoundedCornerShape(12.dp))
    )
}