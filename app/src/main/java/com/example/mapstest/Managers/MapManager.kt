package com.example.mapstest.Managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class MapManager: ViewModel() {
    var cameraPosition by mutableStateOf(CameraPositionState())

    fun updateCameraPosition(location: LatLng) {
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(15f)
            .build()
        this.cameraPosition = CameraPositionState(cameraPosition)
    }
}