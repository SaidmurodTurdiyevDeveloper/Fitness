package uz.smt.fitness_presenter.comp.designs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import uz.smt.fitness_presenter.ui.theme.DarkBlue

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 9:13 PM for Fitnes.
 */
@Composable
fun <T> HorizontalGridItems(
    items: List<T>,
    spanCount: Int = 1,
    modifier: Modifier = Modifier,
    itemView: @Composable BoxScope.(T) -> Unit
) {
    Column(modifier = modifier) {
        val size = items.count()
        val columns = if (size > 0) 1 + (size - 1) / spanCount else 0
        if (0 < columns) {
            for (columnIndex in 0 until columns) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (rowIndex in 0 until spanCount) {
                        val index = columnIndex * spanCount + rowIndex
                        if (size > index) {
                            Box(modifier = Modifier.weight(1f, fill = true), propagateMinConstraints = true) {
                                itemView(items[index])
                            }
                        } else {
                            Spacer(Modifier.weight(1F, fill = true))
                        }
                    }
                }
            }
        }
    }
}
