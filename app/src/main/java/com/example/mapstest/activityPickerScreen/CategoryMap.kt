package com.example.mapstest.activityPickerScreen

import androidx.compose.runtime.Composable
import com.example.mapstest.managers.MapManager
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

// displays a map using Google Maps SDK
// displays all POI selected using CategoryPicker as a Marker
@Composable
fun CategoryMap(mapManager: MapManager)
{
    // camera position state
    val cameraPosition = mapManager.cameraPosition

    // custom map styling
    val properties = MapProperties(
        isBuildingEnabled = true,
        isMyLocationEnabled = true,
        mapStyleOptions = MapStyleOptions(mapStyles)
    )

    GoogleMap(cameraPositionState = cameraPosition, properties = properties)
    {
        // add a marker for every element in our osm search result state list
        mapManager.osmSearchResults.forEach()
        {
            Marker(title = it.name, state = MarkerState(it.coordinate))
        }
    }
}

// https://developers.google.com/maps/documentation/android-sdk/style-reference
// this makes the map view cleaner by disabling POI and transit labels
private val mapStyles =
"""
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