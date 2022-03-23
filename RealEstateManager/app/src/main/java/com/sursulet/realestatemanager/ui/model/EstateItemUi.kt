package com.sursulet.realestatemanager.ui.model

import android.graphics.Bitmap

data class EstateItemUi(
    val image: Bitmap,
    val type: String,
    val city: String,
    val price: String
)
