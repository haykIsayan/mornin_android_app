package com.example.project_mornin.features.mornin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.project_mornin.features.mornin.interests.InterestsGrid
import com.example.project_mornin.features.mornin.interests.InterestsViewModel
import com.example.project_mornin.ui.theme.PrimaryOrange

@Composable
fun MorninScreen(
    interestsViewModel: InterestsViewModel = hiltViewModel(),
    morninViewModel: MorninViewModel = hiltViewModel()
) {
    Column {
        MorninHeader()
        MorninScreenState(
            interestsViewModel,
            morninViewModel
        )
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
                Icons.Filled.Search,
                contentDescription = "bruh",
                modifier = Modifier
                    .size(24.dp)
                    .wrapContentSize(Alignment.Center),
            )
        }
    }
}

@Composable
fun MorninScreenState(
    interestsViewModel: InterestsViewModel,
    morninViewModel: MorninViewModel
) {
   Column {
        InterestsFilter(
            interestsViewModel,
            morninViewModel
        )
        MorninFeed(morninViewModel)
   }
}

@Composable
fun InterestsFilter(
    interestsViewModel: InterestsViewModel,
    morninViewModel: MorninViewModel
) {
    when (val interestsState = interestsViewModel.getState().collectAsState().value) {
        is UiState.Loading -> {
            // Show loading state if needed
        }
        is UiState.Success -> {
            val interests = interestsState.data.interests
            LaunchedEffect(interests) {
                morninViewModel.fetchMorninFeed(interests)
            }
            InterestsGrid(interestsState.data) { selectedTopic ->
                interestsViewModel.updateInterests(selectedTopic)
            }
        }
        is UiState.Error -> {

        }
    }

}

@Composable
fun MorninFeed(morninViewModel: MorninViewModel) {
    when (val state = morninViewModel.uiState.collectAsState().value) {
        is UiState.Loading -> MorninLoading()
        is UiState.Success -> MorninGrid(state.data)
        is UiState.Error -> MorninError(state.message) {
            morninViewModel.fetchMorninFeed()
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
