package com.sursulet.realestatemanager.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sursulet.realestatemanager.R
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import com.sursulet.realestatemanager.data.model.Photo

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Media", style = MaterialTheme.typography.h4)
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(state.gallery) { photo ->
                Row() {
                    Box {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "Estate Image"
                        )
                        Text(
                            text = photo.title, modifier = Modifier
                                .fillMaxWidth()
                                .align(BottomCenter)
                        )
                    }
                }
            }
        }

        Text(text = "Description")
        Text(text = state.type)

        Column {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_settings_overscan_24),
                contentDescription = "Surface"
            )
            Text("Surface")
            Text("Number of rooms")
            Text("Number of bathrooms")
            Text("Number of bedrooms")
        }
        Column {
            Text(text = "Location")
            Text(text = state.price)
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}