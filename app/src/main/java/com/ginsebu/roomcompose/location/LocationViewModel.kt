package com.ginsebu.roomcompose.location

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

class LocationViewModel(
    private val context: Context
): ViewModel() {
    fun onEvent(event: LocationEvent) {
        when(event) {
            LocationEvent.Start -> {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    context.startService(this)
                }
            }
            LocationEvent.Stop -> {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_STOP
                    context.startService(this)
                }
            }
        }
    }
}