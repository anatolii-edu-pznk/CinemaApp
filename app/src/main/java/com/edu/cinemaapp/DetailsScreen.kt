package com.edu.cinemaapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.cinemaapp.models.FilmModel
import com.edu.cinemaapp.ui.theme.primaryText
import com.edu.cinemaapp.widgets.FilmBanner

@Composable
fun DetailsScreen(
    film: FilmModel,
    popBack: () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    FilmBanner(
                        film = film,
                        technology = film.technology,
                        pgRating = film.pgRating,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(ratio = 0.75f),
                    )
                    IconButton(
                        onClick = popBack,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .statusBarsPadding()
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                            .align(Alignment.TopStart)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            }
            item {
                Header(
                    title = film.nameUa,
                    subTitle = "(${film.name})",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp),
                )
            }
            item {
                SubHeader(
                    country = film.country,
                    duration = film.duration,
                    year = "${film.year}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 23.dp),
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                ) {
                    Divider(
                        modifier = Modifier
                            .width(200.dp)
                            .align(Alignment.Center),
                    )
                }
            }
            item {
                Description(
                    title = "Description",
                    text = film.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                )
            }
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(bottom = 32.dp)
                        .padding(horizontal = 16.dp),
                ) {
                    Text(
                        text = "Choose time",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primaryText,
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primaryText,
            textAlign = TextAlign.Center,
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primaryText,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
private fun SubHeader(
    country: String,
    duration: String,
    year: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SubHeaderItem(
            title = "Country",
            text = country,
        )
        SubHeaderItem(
            title = "Duration",
            text = duration,
        )
        SubHeaderItem(
            title = "Year",
            text = year,
        )
    }
}

@Composable
private fun SubHeaderItem(
    title: String,
    text: String,
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryText.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primaryText,
            fontWeight = FontWeight.Normal,
        )
    }
}


@Composable
fun Description(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryText.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primaryText,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    MaterialTheme {
        DetailsScreen(
            film = FilmModel(
                name = "Film name",
                nameUa = "Film name",
                bannerImage = "https://picsum.photos/200/300",
                country = "USA",
                duration = "1h 30m",
                year = 2024,
                language = "English",
                description = "Description",
            )
        )
    }
}