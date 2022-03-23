package com.sursulet.realestatemanager.ui.detail.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sursulet.realestatemanager.R
import com.sursulet.realestatemanager.data.model.Photo

@Composable
fun PhotoItemUi(photo: Photo) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = photo.title)
        Text(text = photo.title)
    }
}

@Preview
@Composable
fun PhotoItemUiPreview() {
    PhotoItemUi(
        Photo(
            title = "Salon",
            image = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888),
            estateId = 1
        )
    )
}