package com.ginsebu.roomcompose.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun FormContactDialog(
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
                   Spacer(modifier = Modifier.height(16.dp))
                   TextField(
                       value = state.lastName,
                       onValueChange = {
                           onEvent(ContactEvent.SetLastName(it))
                       },
                       placeholder = { Text(text = "Last name") }
                   )
                   Spacer(modifier = Modifier.height(16.dp))
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
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    modifier = Modifier
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                    onClick = {
                        onEvent(ContactEvent.HideDialog)
                    }
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = {
                        onEvent(ContactEvent.SaveContact)
                    },
                ) {
                    Text(text = if(state.contact != null) "Update" else "Save")
                }
            }
        }
        )
}