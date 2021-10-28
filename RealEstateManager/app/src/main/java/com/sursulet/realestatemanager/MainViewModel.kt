package com.sursulet.realestatemanager

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.Photo
import com.sursulet.realestatemanager.repository.EstateRepository
import com.sursulet.realestatemanager.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EstateRepository
): ViewModel() {

    init {
        Log.d(TAG, "MAIN VM: ")
        viewModelScope.launch {
            repository.insert(Estate(id = 1, type = "Duplex", price = 150000.0))
            repository.insert(Estate(id = 2, type = "House", price = 170000.0))
            repository.insert(Photo(id = 1, title = "Living Room", estateId = 1))
            repository.insert(Photo(id = 2, title = "Bathroom", estateId = 1))
            repository.insert(Photo(id = 3, title = "Bedroom", estateId = 2))
        }
    }
}