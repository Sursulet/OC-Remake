package com.sursulet.realestatemanager.ui.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sursulet.realestatemanager.R
import com.sursulet.realestatemanager.ui.model.EstateItemUi
import com.sursulet.realestatemanager.ui.theme.Teal200

@Composable
fun EstateItem(
    estate: EstateItemUi
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Estate picture"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {
            Text(text = estate.type, style = MaterialTheme.typography.body1)
            Text(text = estate.city)
            Text(
                text = estate.price,
                color = Teal200,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun EstateItemPreview() {
    EstateItem(
        estate = EstateItemUi(
            image = R.drawable.ic_launcher_foreground,
            type = "Duplex",
            city = "Paris",
            price = "12.0"
        )
    )
}
