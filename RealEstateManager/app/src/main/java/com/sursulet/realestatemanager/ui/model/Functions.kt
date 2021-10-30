package com.sursulet.realestatemanager.ui.model

import com.sursulet.realestatemanager.data.model.EstateItemView
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import com.sursulet.realestatemanager.ui.detail.DetailState

fun EstateItemView.toEstateItemUi() = EstateItemUi(
    image = 1,
    type = type,
    city = "title",
    price = "$price"
)

fun EstateWithPhotos.toDetailState() = DetailState(
    gallery = photos,
    type = estate.type,
    price = estate.price.toString()
)