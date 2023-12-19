package com.ginsebu.roomcompose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ginsebu.roomcompose.contacts.ContactScreen
import com.ginsebu.roomcompose.contacts.ContactViewModel
import com.ginsebu.roomcompose.location.LocationScreen
import com.ginsebu.roomcompose.location.LocationViewModel
import com.ginsebu.roomcompose.ui.theme.RoomComposeTheme

class MainActivity : ComponentActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,

            ),
            0
        )

        setContent {
            RoomComposeTheme {
                val contactViewModel = viewModels<ContactViewModel> {
                    viewModelFactory {
                        ContactViewModel(MainApp.appModule.db.dao)
                    }
                }.value
                val state by contactViewModel.state.collectAsState()

                val locationViewModel = viewModels<LocationViewModel> {
                    viewModelFactory {
                        LocationViewModel(applicationContext)
                    }
                }.value

                Surface {
                    // Navigation
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "contacts"
                    ) {
                        composable("contacts") {
                            ContactScreen(state = state, onEvent = contactViewModel::onEvent, navController = navController)
                        }

                        composable("location") {
                            LocationScreen(onEvent = locationViewModel::onEvent, navController = navController)
                        }
                    }
                }
            }
        }
    }
}