package com.sursulet.realestatemanager.ui.addedit

sealed class AddEditEstateEvent {
    data class Type(val value: String) : AddEditEstateEvent()
    data class Price(val value: String) : AddEditEstateEvent()
    object Save : AddEditEstateEvent()
}
