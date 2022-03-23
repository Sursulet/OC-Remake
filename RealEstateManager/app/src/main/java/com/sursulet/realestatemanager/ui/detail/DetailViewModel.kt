package com.sursulet.realestatemanager.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sursulet.realestatemanager.di.IoDispatcher
import com.sursulet.realestatemanager.repository.EstateRepository
import com.sursulet.realestatemanager.ui.model.toDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: EstateRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        viewModelScope.launch(dispatcher) {
            savedStateHandle.get<Long>("estateId")?.let { estateId ->
                repository.getEstate(estateId).collect {
                    _state.value = it.toDetailState()
                }
            }
        }
    }
}