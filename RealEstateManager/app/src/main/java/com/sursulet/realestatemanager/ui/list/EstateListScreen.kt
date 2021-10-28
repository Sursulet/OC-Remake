package com.sursulet.realestatemanager.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sursulet.realestatemanager.ui.list.components.EstateItem

@Composable
fun EstateListScreen(
    viewModel: EstateListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.estates) { estate ->
                EstateItem(estate = estate)
            }
        }
    }
}