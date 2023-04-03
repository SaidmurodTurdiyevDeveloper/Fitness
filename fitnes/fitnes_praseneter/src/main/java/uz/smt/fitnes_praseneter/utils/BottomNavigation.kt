package uz.smt.fitnes_praseneter.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import uz.smt.fitnes_praseneter.model.BottomNavItem
import uz.smt.fitnes_praseneter.ui.theme.ColorAccent
import uz.smt.fitnes_praseneter.ui.theme.SelectColor

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:13 PM for Fitnes.
 */
@Composable
fun BottomNavigationItemView(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    selectedColor: Color = SelectColor,
    unSelectedColor: Color = ColorAccent
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            val color = if (selected) selectedColor
            else unSelectedColor
            BottomNavigationItem(selected = selected, onClick = {
                onItemClick.invoke(item)
            },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(
                                    text = item.badgeCount.toString(),
                                    fontSize = 9.sp
                                )
                            }) {
                                Icon(imageVector = item.icon, tint = color, contentDescription = "Image bottom navigation")
                            }
                        } else {
                            Icon(imageVector = item.icon, tint = color, contentDescription = "Image bottom navigation")
                        }
                        Text(
                            text = item.name, style = TextStyle(
                                color = color,
                                fontSize = 11.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                })
        }
    }
}