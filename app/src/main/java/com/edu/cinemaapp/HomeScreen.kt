package com.edu.cinemaapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edu.cinemaapp.models.FilmModel
import com.edu.cinemaapp.widgets.FilmBanner

@Composable
fun HomeScreen(
    onFilmClicked: (FilmModel) -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            BannerPager(
                onFilmClicked = onFilmClicked,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
                    .weight(0.7f),
            )
            Text(
                text = "Soon in cinemas",
                modifier = Modifier.fillMaxWidth(),
            )
            UpcomingLazyList(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f)
            )
        }
    }
}

@Composable
fun BannerPager(
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
    ) { page ->
        FilmBanner(
            film = FilmModel(name = "Film №$page"),
            technology = "2D",
            pgRating = "0+",
            onFilmClicked = onFilmClicked,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun UpcomingLazyList(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, bottom = 8.dp, end = 8.dp),

        modifier = modifier
            .background(Color.Yellow.copy(alpha = 0.5f)),
    ) {
        items(10) {
            FilmCard(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(width = 164.dp)
                    .fillMaxHeight(),
            )
        }
    }
}

@Composable
fun FilmCard(
    modifier: Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Film poster",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize(),
            )
            Text(
                text = "Film info",
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(33.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary
                    ),
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}