package com.ginsebu.roomcompose.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    navController: NavHostController,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        },
        modifier = Modifier.padding(16.dp),
    ) { padding ->
        if(state.isAddingContact) {
            FormContactDialog(state = state, onEvent = onEvent)
        }

        if(state.isDeleteContact) {
            DeleteContactDialog(state = state, onEvent = onEvent)
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = {
                    navController.navigate("location")
                },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location")
            }
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            // Radio Button
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SortType.values().forEach { sortType ->
                        Row (
                            modifier = Modifier
                                .clickable {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                }
                            )
                            Text(
                                text = sortType.name,
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        }
                    }
                }
            }

            // List Contacts
            items(state.contacts) { contact ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Column (
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${contact.firstName} ${contact.lastName}",
                            fontSize = 20.sp,
                            )
                        Text(text = contact.phoneNumber, fontSize = 12.sp)
                    }
                    // Edit Icon
                    IconButton(onClick = {
                        onEvent(ContactEvent.EditContact(contact))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Contact",
                            tint = Color.Magenta,
                        )
                    }
                    // Delete Icon
                    IconButton(onClick = {
                        onEvent(ContactEvent.ConfirmDeleteContact(contact))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Contact",
                            tint = Color.Red,
                        )
                    }
                }
            }
        }

    }
}