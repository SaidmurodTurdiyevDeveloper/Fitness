@file:OptIn(ExperimentalMaterialApi::class)

package uz.smt.fitness_presenter.comp.drawer

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.smt.fitness_domen.model.ReminderData
import uz.smt.fitness_domen.model.TimeData
import uz.smt.fitness_presenter.comp.designs.RoundButton
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow
import uz.smt.fitness_presenter.viewmodel.ViewModelReminder

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/23/2023 9:56 PM for Fitnes.
 */
@Composable
fun ReminderDrawerScreen(
    navHostController: NavHostController,
    viewModel: ViewModelReminder = hiltViewModel()
) {
    val state = viewModel.state.value

    var newData by remember {
        mutableStateOf(
            ReminderData(
                id = "",
                isRemindActive = true,
                time = TimeData(
                    minute = 0, clock = 8, text = "08:00"
                ),
                isRemindOnMonday = false,
                isRemindOnTuesday = false,
                isRemindOnWednesday = false,
                isRemindOnThursday = false,
                isRemindOnFriday = false,
                isRemindOnSaturday = false,
                isRemindOnSunday = false
            )
        )
    }

    val context = LocalContext.current

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, animationSpec = tween(
            durationMillis = 200, delayMillis = 0, easing = FastOutLinearInEasing
        )
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ViewModelReminder.ViewModelEvent.OpenBottomSheet -> {
                    newData = event.data ?: ReminderData(
                        id = "",
                        isRemindActive = true,
                        time = TimeData(
                            minute = 0, clock = 8, text = "08:00"
                        ),
                        isRemindOnMonday = false,
                        isRemindOnTuesday = false,
                        isRemindOnWednesday = false,
                        isRemindOnThursday = false,
                        isRemindOnFriday = false,
                        isRemindOnSaturday = false,
                        isRemindOnSunday = false
                    )
                    bottomSheetState.show()
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetContent = {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (newData.id.isNotEmpty()) "Update Item" else "New Item",
                            color = DarkBlue,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(onClick = {
                                dialogTimePicker(timeData = newData.time,
                                    context = context,
                                    setTime = {
                                        newData = newData.copy(time = it)
                                    })
                            }) {
                                Text(
                                    text = newData.time.text,
                                    color = Yellow,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Switch(
                                checked = newData.isRemindActive,
                                onCheckedChange = { newData = newData.copy(isRemindActive = it) },
                                colors = SwitchDefaults.colors(checkedThumbColor = Yellow)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        WeekCheckBox(
                            name = "Monday",
                            isChecked = newData.isRemindOnMonday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnMonday = isChecked)
                            }
                        )
                        WeekCheckBox(name = "Tuesday",
                            isChecked = newData.isRemindOnTuesday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnTuesday = isChecked)
                            })
                        WeekCheckBox(
                            name = "Wednesday",
                            isChecked = newData.isRemindOnWednesday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnWednesday = isChecked)
                            })
                        WeekCheckBox(name = "Thursday",
                            isChecked = newData.isRemindOnThursday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnThursday = isChecked)
                            })
                        WeekCheckBox(name = "Friday",
                            isChecked = newData.isRemindOnFriday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnFriday = isChecked)
                            })
                        WeekCheckBox(name = "Saturday",
                            isChecked = newData.isRemindOnSaturday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnSaturday = isChecked)
                            })
                        WeekCheckBox(name = "Sunday",
                            isChecked = newData.isRemindOnSunday,
                            modifier = Modifier.fillMaxWidth(),
                            onCheck = { isChecked ->
                                newData = newData.copy(isRemindOnSunday = isChecked)
                            })
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            RoundButton(
                                text = if (newData.id.isEmpty()) "Save" else "Update",
                                modifier = Modifier.fillMaxWidth(0.6f),
                                backgroundColor = Yellow,
                                textStyle = TextStyle(
                                    color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold
                                ),
                            ) {
                                scope.launch {
                                    bottomSheetState.hide()
                                    viewModel.onEvent(ViewModelReminder.UIEvent.SaveData(newData))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }
        }, sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp, topEnd = 16.dp
        )
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(ViewModelReminder.UIEvent.AddNewItem)
                    },
                    backgroundColor = Yellow,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "Add icon", tint = Color.White
                    )
                }
            }, floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.reminderList) { item ->
                    ItemReminder(item = item, onClick = {
                        viewModel.onEvent(ViewModelReminder.UIEvent.ItemClick(item))
                    }, onDelete = {
                        viewModel.onEvent(ViewModelReminder.UIEvent.ItemDelete(item.id))
                    }, changesIsActive = { isActive ->
                        viewModel.onEvent(ViewModelReminder.UIEvent.ChangeIsActive(item, isActive))
                    })
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemReminder(
    item: ReminderData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    changesIsActive: (Boolean) -> Unit
) {
    val repeatWeekText = repeatWeekText(item)
    Surface(modifier = modifier, elevation = 0.dp, onClick = onClick) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.time.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )
                Switch(
                    checked = item.isRemindActive,
                    onCheckedChange = changesIsActive,
                    colors = SwitchDefaults.colors(checkedThumbColor = Yellow)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Repeat",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )
                    Text(
                        text = repeatWeekText,
                        fontSize = 14.sp,
                        color = DarkBlue
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Icon delete",
                        tint = Yellow
                    )
                }
            }
        }
    }
}

private fun repeatWeekText(item: ReminderData): String {
    val text = StringBuilder()
    if (item.isRemindOnMonday) text.append("Mon")
    if (item.isRemindOnTuesday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Tue")
    }
    if (item.isRemindOnWednesday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Wed")
    }
    if (item.isRemindOnThursday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Thu")
    }
    if (item.isRemindOnFriday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Fri")
    }
    if (item.isRemindOnSaturday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Sat")
    }
    if (item.isRemindOnSunday) {
        if (text.isNotEmpty()) text.append(",")
        text.append("Sun")
    }
    return text.toString()
}

@Composable
private fun WeekCheckBox(
    name: String, isChecked: Boolean,
    onCheck: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = DarkBlue,
        fontSize = 16.sp, fontWeight = FontWeight.Bold
    )
) {
    Row(
        modifier = modifier.clickable {
            onCheck.invoke(isChecked.not())
        }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = textStyle)
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheck,
            colors = CheckboxDefaults.colors(checkedColor = Yellow)
        )
    }
}

private fun dialogTimePicker(
    timeData: TimeData, context: Context, setTime: (TimeData) -> Unit
) {
    val timePickerDialog = TimePickerDialog(
        context, { _, hour: Int, minute: Int ->
            setTime(TimeData(minute, hour, "$hour:$minute"))
        }, timeData.clock, timeData.minute, true
    )
    timePickerDialog.show()
}