package uz.smt.fitness_presenter.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.smt.fitness_presenter.R
import uz.smt.fitness_presenter.dialog.RestartProgressDialog
import uz.smt.fitness_presenter.model.MenuItem
import uz.smt.fitness_presenter.navigation.DrawerNavHost
import uz.smt.fitness_presenter.navigation.DrawerScreens
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/15/2023 5:23 PM for Fitnes.
 */
@Composable
fun HomeScreen(
    drawerNavHostController: NavHostController,
    navHostController: NavHostController
) {
    var title by remember {
        mutableStateOf("Home Screen")
    }
    val context = LocalContext.current
    var showCustomDialog by remember {
        mutableStateOf(false)
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        tint = DarkBlue,
                        contentDescription = "Icon Menu"
                    )
                }
            }, title = {
                Text(text = title, color = DarkBlue)
            }, backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        drawerContent = {
            DrawerHeader(
                title = title,
                image = painterResource(id = R.drawable.training_men),
                modifier = Modifier.height(200.dp)
            )
            DrawerBody(items = getDrawerMenuItem(),
                onClick = { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    if (item.route != null) {
                        drawerNavHostController.navigate(item.route.route)
                    } else {
                        showCustomDialog = !showCustomDialog
                    }
                })
        }
    ) { innerPadding ->
        DrawerNavHost(modifier = Modifier.padding(innerPadding),
            navHostController = navHostController,
            drawerNavHost = drawerNavHostController,
            onTitleChange = { newTitle ->
                title = newTitle
            }
        )
    }
    if (showCustomDialog) {
        RestartProgressDialog({
            showCustomDialog = !showCustomDialog
        }, {
            showCustomDialog = !showCustomDialog
        })
    }
}

@Composable
private fun DrawerHeader(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    headerTextStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Yellow)
    ) {
        Image(
            painter = image,
            contentDescription = "Header image",
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
                .background(Color.Black)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
                .padding(32.dp), contentAlignment = Alignment.BottomStart
        ) {
            Text(text = title, style = headerTextStyle)
        }
    }
}

@Composable
private fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    onClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke(item)
                }
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = item.icon, tint = DarkBlue, contentDescription = "Icon Menu")
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = item.title, color = DarkBlue)
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Next", tint = DarkBlue)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(color = DarkBlue, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun getDrawerMenuItem(): List<MenuItem> {
    return listOf(
        MenuItem(route = DrawerScreens.HomeWorkout, "Training Plans", icon = ImageVector.vectorResource(id = R.drawable.time_icon)),
        MenuItem(route = DrawerScreens.Discover, "Discover", icon = ImageVector.vectorResource(id = R.drawable.search_from_list_icon)),
        MenuItem(route = DrawerScreens.Report, "Report", icon = ImageVector.vectorResource(id = R.drawable.line_chart_icon)),
        MenuItem(route = DrawerScreens.Reminder, "Remainder", icon = ImageVector.vectorResource(id = R.drawable.time_icon)),
        MenuItem(route = DrawerScreens.Language, "Language", icon = ImageVector.vectorResource(id = R.drawable.language_icon)),
        MenuItem(route = DrawerScreens.Setting, "Setting", icon = ImageVector.vectorResource(id = R.drawable.setting_icon)),
        MenuItem(route = null, "Restart progress", icon = ImageVector.vectorResource(id = R.drawable.restart_icon)),
    )
}
