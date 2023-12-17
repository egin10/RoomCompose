package com.ginsebu.roomcompose.location

sealed interface LocationEvent {
    object Start: LocationEvent
    object Stop: LocationEvent
}