package com.sursulet.realestatemanager.ui.addedit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.ui.addedit.components.AddEditTextField

@Composable
fun AddEditEstateScreen(
    //viewModel: AddEditEstateViewModel = hiltViewModel()
) {
    //val state = viewModel.state.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*viewModel.onEvent(AddEditEstateEvent.Save)*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Estate")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(listOf<Estate>()) {}
            }

            AddEditTextField(
                text = "state.type",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { /*viewModel.onEvent(AddEditEstateEvent.Type(it))*/ })
            Spacer(modifier = Modifier.height(16.dp))

            AddEditTextField(
                text = "state.price",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { /*viewModel.onEvent(AddEditEstateEvent.Price(it))*/ })
        }
    }
}

@Preview
@Composable
fun AddEditEstateScreenPreview() {
    AddEditEstateScreen()
}