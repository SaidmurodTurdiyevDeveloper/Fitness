package uz.smt.fitnes_praseneter.screen.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.smt.fitnes_praseneter.model.DataStats

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/8/2023 4:22 PM for Fitnes.
 */
@Composable
fun ItemViewStats(
    modifier: Modifier = Modifier,
    item: DataStats,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .clickable {
                onClick.invoke(item.id)
            }
            .background(color = item.color, shape = RoundedCornerShape(16.dp))
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp), ambientColor = item.color, spotColor = item.color)
            .padding(start = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name, color = Color.White, fontSize = 12.sp, maxLines = 2)
            Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.2f)
                        .background(color = Color.White, shape = CircleShape)
                )
                Icon(imageVector = ImageVector.vectorResource(id = item.icon), tint = Color.White, contentDescription = item.name)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = item.stats,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = item.statsInfo,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold, maxLines = 1,
                modifier = Modifier.padding(bottom = 1.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Spacer(modifier = Modifier.width(56.dp))
            Text(
                text = item.info + "\n",
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 2,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
