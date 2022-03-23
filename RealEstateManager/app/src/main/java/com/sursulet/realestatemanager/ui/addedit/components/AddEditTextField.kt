package com.sursulet.realestatemanager.ui.addedit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddEditTextField(text: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            isError = false,
            leadingIcon = {
                Icon(imageVector = Icons.Default.AttachMoney, contentDescription = "")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "",
                    modifier = Modifier.clickable { /* text = "" */ })
            },
            label = { Text(text = "Price") })
    }
}