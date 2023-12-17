package com.ginsebu.roomcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = if(state.contact != null) "Edit Contact" else "Add New Contact") },
        text = {
               Column(
                   verticalArrangement = Arrangement.SpaceBetween
               ) {
                    TextField(
                        value = state.firstName,
                        onValueChange = {
                            onEvent(ContactEvent.SetFirstName(it))
                        },
                        placeholder = { Text(text = "First name") }
                        )
                   TextField(
                       value = state.lastName,
                       onValueChange = {
                           onEvent(ContactEvent.SetLastName(it))
                       },
                       placeholder = { Text(text = "Last name") }
                   )
                   TextField(
                       value = state.phoneNumber,
                       onValueChange = {
                           onEvent(ContactEvent.SetPhoneNumber(it))
                       },
                       placeholder = { Text(text = "Phone Number") }
                   )
               }
        },
        onDismissRequest = { onEvent(ContactEvent.HideDialog) },
        confirmButton = {
            Box (
                modifier = Modifier.fillMaxWidth()
            ){
                Button(onClick = {
                    onEvent(ContactEvent.SaveContact)
                }) {
                    Text(text = if(state.contact != null) "Update" else "Save")
                }
            }
        }
        )
}