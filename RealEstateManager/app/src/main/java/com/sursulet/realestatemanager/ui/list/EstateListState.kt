package com.sursulet.realestatemanager.ui.list

import com.sursulet.realestatemanager.ui.model.EstateItemUi

data class EstateListState(
    val isLoading: Boolean = false,
    val estates: List<EstateItemUi> = emptyList(),
    val error: String = ""
)
