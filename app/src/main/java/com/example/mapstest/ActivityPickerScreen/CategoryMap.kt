package com.example.mapstest.ActivityPickerScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.example.mapstest.Managers.MapManager
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@SuppressLint("MissingPermission")
@Composable
fun CategoryMap(mapManager: MapManager)
{
    // state variable that hold the camera position
    val cameraPosition = mapManager.cameraPosition

    // bi-directional data flow from CategoryMap <-> GoogleMap
    GoogleMap(cameraPositionState = cameraPosition, properties = MapProperties(mapStyleOptions = MapStyleOptions(styleJson)))
    {
        mapManager.osmSearchResults.forEach()
        { poi ->
            Marker(title = poi.name,
                state = MarkerState(
                    position = LatLng(
                        poi.coordinate.latitude,
                        poi.coordinate.longitude
                    )
                )
            )
        }

    }
}

val styleJson = """
    [
        {
            "featureType": "transit",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        },
        {
            "featureType": "poi",
            "elementType": "labels",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        }
    ]
""".trimIndent()