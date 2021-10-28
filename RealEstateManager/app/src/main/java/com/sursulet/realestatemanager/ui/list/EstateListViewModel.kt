package com.sursulet.realestatemanager.ui.list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sursulet.realestatemanager.di.IoDispatcher
import com.sursulet.realestatemanager.repository.EstateRepository
import com.sursulet.realestatemanager.ui.model.toEstateItemUi
import com.sursulet.realestatemanager.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstateListViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: EstateRepository
) : ViewModel() {

    private val _state = mutableStateOf(EstateListState())
    val state: State<EstateListState> = _state

    init {
        viewModelScope.launch {
                repository.getEstatesView().collect {
                Log.d(TAG, "VM: $it")
                _state.value = EstateListState(estates = it.map { estate -> estate.toEstateItemUi() })
            }
        }
    }
}