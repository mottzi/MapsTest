package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mapstest.Managers.MapManager
import com.google.maps.android.compose.GoogleMap

@Composable
fun CategoryMap(mapManager: MapManager) {
    val cameraPositionState by mapManager.cameraPositionState.collectAsState()
//    val currentLocation by mapManager.currentLocation.collectAsState()

    Box(modifier = Modifier.fillMaxSize())
    {
        GoogleMap(cameraPositionState = cameraPositionState) {

        }
    }
}