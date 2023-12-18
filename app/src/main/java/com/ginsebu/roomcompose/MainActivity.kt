package com.ginsebu.roomcompose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ginsebu.roomcompose.contacts.ContactDatabase
import com.ginsebu.roomcompose.contacts.ContactScreen
import com.ginsebu.roomcompose.contacts.ContactViewModel
import com.ginsebu.roomcompose.location.LocationButton
import com.ginsebu.roomcompose.location.LocationViewModel
import com.ginsebu.roomcompose.ui.theme.RoomComposeTheme

class MainActivity : ComponentActivity() {

//    private val db by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            ContactDatabase::class.java,
//            "contacts.db",
//        ).build()
//    }

//    private val viewModel by viewModels<ContactViewModel>(
//        factoryProducer = {
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return ContactViewModel(db.dao) as T
//                }
//            }
//        }
//    )
//
//    private val locationViewModel by viewModels<LocationViewModel>(
//        factoryProducer = {
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return LocationViewModel(applicationContext) as T
//                }
//            }
//        }
//    )



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

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ContactScreen(state = state, onEvent = contactViewModel::onEvent, onLocationEvent = locationViewModel::onEvent)
                }

            }
        }
    }
}