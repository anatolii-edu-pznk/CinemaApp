package com.edu.cinemaapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.edu.cinemaapp.models.FilmModel
import com.edu.cinemaapp.ui.theme.primaryText
import com.edu.cinemaapp.widgets.FilmBanner
import com.edu.cinemaapp.widgets.ShimmerEffect

@Composable
fun HomeScreen(
    state: HomeState,
    onFilmClicked: (FilmModel) -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            BannerPager(
                state = state,
                onFilmClicked = onFilmClicked,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
            )
            Text(
                text = "Soon in cinemas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryText,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
            )
            UpcomingLazyList(
                state = state,
                onFilmClicked = onFilmClicked,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f)
                    .navigationBarsPadding()
            )
        }
    }
}

@Composable
fun BannerPager(
    state: HomeState,
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    if (state.releasedFilms.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = { state.releasedFilms.size })
        HorizontalPager(
            state = pagerState,
            modifier = modifier,
        ) { page ->
            FilmBanner(
                film = state.releasedFilms[page],
                technology = state.releasedFilms[page].technology,
                pgRating = state.releasedFilms[page].pgRating,
                onFilmClicked = onFilmClicked,
                modifier = Modifier.fillMaxSize(),
            )
        }
    } else {
        ShimmerEffect(
            modifier = modifier.background(Color.LightGray),
        )
    }
}

@Composable
fun UpcomingLazyList(
    state: HomeState,
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    if (state.upcomingFilms.isNotEmpty()) {
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp, bottom = 8.dp, end = 8.dp),
            modifier = modifier
        ) {
            items(state.upcomingFilms) { film ->
                FilmCard(
                    film = film,
                    onFilmClicked = onFilmClicked,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .fillMaxHeight()
                        .aspectRatio(ratio = 0.67f),
                )
            }
        }
    } else {
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp, bottom = 8.dp, end = 8.dp),
            modifier = modifier
        ) {
            items(count = 5) {
                ShimmerEffect(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(shape = MaterialTheme.shapes.extraSmall)
                        .background(Color.LightGray)
                        .fillMaxHeight()
                        .aspectRatio(ratio = 0.67f),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilmCard(
    film: FilmModel,
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier,
) {
    Card(
        onClick = { onFilmClicked(film) },
        shape = MaterialTheme.shapes.extraSmall,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = film.bannerImage,
                contentDescription = film.description,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize(),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(color = MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = film.nameUa,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(state = HomeState())
    }
}