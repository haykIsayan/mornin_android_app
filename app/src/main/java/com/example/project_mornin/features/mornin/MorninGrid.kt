package com.example.project_mornin.features.mornin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.entity.MorninTopic
import com.example.project_mornin.ui.theme.NeutralGray

data class MorninItemUiData(
    val mornin: MorninEntity,
    val size: Int
)

@Composable
fun MorninGrid(mornins: List<MorninEntity>) {
    val morninUiDataList = mornins.map {
        MorninItemUiData(
            it,
            (200..300).random()
        )
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(morninUiDataList) {
            MorninItem(it)
        }
    }
}

@Composable
fun MorninItem(morninItemUiData: MorninItemUiData) {
    val mornin = morninItemUiData.mornin
    val size = morninItemUiData.size
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(mornin.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                Text(
                    text = mornin.topic.toString(),
                    modifier = Modifier.padding(vertical = 1.dp, horizontal = 8.dp),
                    fontSize = 8.sp,
                    maxLines = 1,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = mornin.author,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = NeutralGray,
                fontSize = 12.sp,
                maxLines = 2,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis
            )
//            Text("•")
//            Text(
//                text = mornin.date,
//                modifier = Modifier,
//                textAlign = TextAlign.Start,
//                fontWeight = FontWeight.Bold,
//                color = NeutralGray,
//                fontSize = 12.sp,
//                maxLines = 2,
//                lineHeight = 16.sp,
//                overflow = TextOverflow.Ellipsis
//            )
//            Icon(
//                imageVector = getMorninTopicIcon(mornin.topic),
//                contentDescription = "lyol",
//                modifier = Modifier
//                    .size(12.dp)
//            )
        }
        Text(
            text = mornin.title,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 2,
            lineHeight = 16.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}