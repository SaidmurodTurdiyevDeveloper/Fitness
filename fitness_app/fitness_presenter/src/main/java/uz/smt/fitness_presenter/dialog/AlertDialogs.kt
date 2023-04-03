package uz.smt.fitness_presenter.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.smt.fitness_presenter.ui.theme.DarkBlue
import uz.smt.fitness_presenter.ui.theme.Yellow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/29/2023 8:36 PM for Fitnes.
 */
@Composable
fun RestartProgressDialog(
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {

    Dialog(onDismissRequest = {
        onDismiss()
    }) {
        Surface(modifier = Modifier, shape = RoundedCornerShape(4.dp)) {
            Column(
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 4.dp
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh Icon",
                        tint = Yellow,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Restart Progress",
                        color = DarkBlue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = {
                            onDismiss()
                        },
                        border = BorderStroke(width = 1.dp, color = Yellow),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = Yellow,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = {
                            onSuccess()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                    ) {
                        Text(
                            text = "Ok", style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }

}