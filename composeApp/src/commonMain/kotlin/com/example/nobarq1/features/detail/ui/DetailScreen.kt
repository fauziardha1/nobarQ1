package com.example.nobarq1.features.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.nobarq1.core.designsystem.NetflixRed
import com.example.nobarq1.features.home.domain.MovieDetail
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    onBack: () -> Unit,
    viewModel: DetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetail(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack, 
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (val detailState = state) {
                is DetailState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is DetailState.Success -> {
                    DetailContent(
                        movie = detailState.movie,
                        isFavorite = isFavorite,
                        onToggleFavorite = { viewModel.toggleFavorite(detailState.movie) },
                        onPlayTrailer = { key ->
                            uriHandler.openUri("https://www.youtube.com/watch?v=$key")
                        }
                    )
                }
                is DetailState.Error -> {
                    Text(
                        text = detailState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    movie: MovieDetail,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onPlayTrailer: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.backdropPath}",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
                            startY = 100f
                        )
                    )
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) NetflixRed else Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Star, 
                    contentDescription = null, 
                    tint = Color.Yellow, 
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${movie.voteAverage.toString().take(3)} | ${movie.releaseDate.take(4)} | ${movie.runtime}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { movie.trailerKey?.let { onPlayTrailer(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = MaterialTheme.shapes.extraSmall,
                enabled = movie.trailerKey != null
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Play Trailer", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Genres: ${movie.genres.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
