package com.sursulet.realestatemanager.data.model

import android.graphics.Bitmap
import androidx.room.DatabaseView

@DatabaseView("SELECT estate.id,estate.type, estate.price, photo.image FROM estate INNER JOIN photo ON photo.id = (SELECT photo.id FROM photo WHERE estate.id = photo.estateId LIMIT 1)")
data class EstateItemView(
    val id: Long,
    val type: String,
    val price: Double,
    val image: Bitmap
)
