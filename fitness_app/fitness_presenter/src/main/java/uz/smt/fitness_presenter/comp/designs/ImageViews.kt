package uz.smt.fitness_presenter.comp.designs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import uz.smt.fitness_domen.model.BackgroundColor
import uz.smt.fitness_domen.model.ImageData
import uz.smt.fitness_presenter.ui.theme.*

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/26/2023 1:54 PM for Fitnes.
 */
@Composable
fun ImageViewWithBackground(
    modifier: Modifier = Modifier,
    image: ImageData,
    corner: Dp = 16.dp,
    backgroundRadius: Float =400f
) {
    val backgroundColor=when(image.backGroundColor){
        BackgroundColor.Yellow -> Yellow
        BackgroundColor.Red -> Red
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(corner),
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f)
                .background(
                    brush = Brush.radialGradient(
                        listOf(
                            Color.Transparent,
                            Black_70
                        ),
                        radius = backgroundRadius
                    )
                )
        )
        if (image.imageRes != null)
            Image(
                painter = painterResource(id = image.imageRes!!),
                contentDescription = "Header image",
                modifier = Modifier.fillMaxSize()
            )
        else if (image.imageNetworkUrl != null)
            AsyncImage(
                model = image.imageNetworkUrl!!,
                contentDescription = "Header image",
                modifier = Modifier.fillMaxSize()
            )
    }
}