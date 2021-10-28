package com.sursulet.realestatemanager.ui.detail

import com.sursulet.realestatemanager.data.model.Photo

data class DetailState(
    val isLoading: Boolean = false,
    val gallery: List<Photo> = emptyList(),
    val type: String = "",
    val price: String = "",
    val error: String = ""
)
