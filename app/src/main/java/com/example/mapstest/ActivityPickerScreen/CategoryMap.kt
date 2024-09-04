package com.example.mapstest.ActivityPickerScreen

import androidx.compose.runtime.Composable
import com.example.mapstest.Managers.MapManager
import com.google.maps.android.compose.GoogleMap

@Composable
fun CategoryMap(mapManager: MapManager)
{
    // state variable that hold the camera position
    val cameraPosition = mapManager.cameraPosition

    // bi-directional data flow from CategoryMap <-> GoogleMap
    GoogleMap(cameraPositionState = cameraPosition)
}