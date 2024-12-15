package com.edu.cinemaapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onFilmClicked: (FilmModel) -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    PullToRefreshBox(
        state = rememberPullToRefreshState(),
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
}

@Composable
fun BannerPager(
    state: HomeState,
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    if (state.releasedFilms.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = { state.releasedFilms.size })
        Box(modifier = modifier) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                FilmBanner(
                    film = state.releasedFilms[page],
                    technology = state.releasedFilms[page].technology,
                    pgRating = state.releasedFilms[page].pgRating,
                    onFilmClicked = onFilmClicked,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            PagerIndicatorRow(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
            )
        }
    } else {
        ShimmerEffect(
            modifier = modifier.background(Color.LightGray),
        )
    }
}

@Composable
private fun PagerIndicatorRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pagerState.pageCount) { page ->
            val color = MaterialTheme.colorScheme.primary.copy(
                alpha = if (pagerState.currentPage == page) 1f else 0.5f
            )
            val size = if (pagerState.currentPage == page) 16.dp else 12.dp
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size = size)
                    .clip(CircleShape)
                    .background(color = color),
            )
        }
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