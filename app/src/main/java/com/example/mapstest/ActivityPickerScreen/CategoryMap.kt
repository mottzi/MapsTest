package com.example.mapstest.ActivityPickerScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.mapstest.Managers.MapManager
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap

@SuppressLint("MissingPermission")
@Composable
fun CategoryMap(mapManager: MapManager)
{
    // application context
    val context = LocalContext.current
    // state variable that hold the camera position
    val cameraPosition = mapManager.cameraPosition

    LaunchedEffect(key1 = Unit)
    {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener()
        { location ->
            if (location != null)
            {
                mapManager.updateCameraPosition(LatLng(location.latitude, location.longitude))
            }
        }
    }

    // bi-directional data flow from CategoryMap <-> GoogleMap
    GoogleMap(cameraPositionState = cameraPosition)
}