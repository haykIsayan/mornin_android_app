package com.example.project_mornin.features.mornin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project_mornin.R
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.ui.theme.NeutralGray
import com.example.project_mornin.ui.theme.PrimaryOrange

@Composable
fun MorninScreen(
    viewModel: MorninViewModel = hiltViewModel()
) {
    Column {
        MorninHeader()
        MorninContent(viewModel = viewModel)
    }
}

@Composable
fun MorninHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.cup_hot_svgrepo_com),
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(34.dp)
                    .wrapContentSize(Alignment.Center),
                tint = PrimaryOrange
            )
            Text(
                text = stringResource(R.string.app_name),

                color = PrimaryOrange,
                letterSpacing = 4.sp,
                fontSize = 28.sp,
                fontWeight = FontWeight.W900,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "bruh",
                modifier = Modifier
//                    .padding(8.dp)
                    .size(24.dp)
                    .wrapContentSize(Alignment.Center),
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "bruh",
                modifier = Modifier
//                    .padding(8.dp)
                    .size(24.dp)
                    .wrapContentSize(Alignment.Center),
            )
        }
    }



}

@Composable
fun MorninContent(
    viewModel: MorninViewModel
) {
    when (val state = viewModel.uiState.collectAsState().value) {
        is MorninUiState.Loading -> MorninLoading()
        is MorninUiState.Success -> MorninContent(state.data)
        is MorninUiState.Error -> MorninError(state.message) {
            viewModel.fetchMorninFeed()
        }
    }
}

@Composable
fun MorninLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MorninContent(data: List<MorninEntity>) {
    MorninGrid(mornins = data)
}

@Composable
fun MorninError(
    message: String,
    onTryAgain: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {

        Text(
            text = "Error: $message",
            Modifier
                .clickable {
                    onTryAgain()
                }
        )

    }
}