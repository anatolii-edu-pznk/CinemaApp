package com.edu.cinemaapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.edu.cinemaapp.models.FilmModel
import com.edu.cinemaapp.ui.theme.primaryText

@Composable
fun FilmBanner(
    film: FilmModel,
    technology: String,
    pgRating: String,
    onFilmClicked: (FilmModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = Color.Red.copy(alpha = 0.5f),
        onClick = { onFilmClicked(film) },
    ) {
        Box {
            AsyncImage(
                model = film.bannerImage,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize(),
            )

            Badge(
                text = technology,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .statusBarsPadding()
                    .align(Alignment.TopStart)
                    .offset(x = 9.dp, y = 46.dp)
            )

            Badge(
                text = pgRating,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .statusBarsPadding()
                    .align(Alignment.TopStart)
                    .offset(x = 9.dp, y = 96.dp)
            )
        }
    }
}

@Composable
private fun Badge(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(42.dp)
            .clip(RoundedCornerShape(8.dp))
            .shadow(elevation = 2.dp)
            .background(color = Color.White,),
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primaryText,
        )
    }
}

@Preview
@Composable
private fun FilmBannerPreview() {
    MaterialTheme {
        FilmBanner(
            film = FilmModel(name = "Film name"),
            technology = "2D",
            pgRating = "0+",
            modifier = Modifier.fillMaxSize(),
        )
    }
}
