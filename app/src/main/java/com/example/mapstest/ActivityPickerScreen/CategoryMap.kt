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
import java.util.UUID

data class OSMPointOfInterest(
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val location: LatLng
)

val initialPOI: List<OSMPointOfInterest> = listOf(
    OSMPointOfInterest(
        name = "Pel",
        location = LatLng(47.521293291032165, 7.693783446277529)
    ),
)

@SuppressLint("MissingPermission")
@Composable
fun CategoryMap(mapManager: MapManager)
{
    // state variable that hold the camera position
    val cameraPosition = mapManager.cameraPosition

    // bi-directional data flow from CategoryMap <-> GoogleMap
    GoogleMap(cameraPositionState = cameraPosition, properties = MapProperties(mapStyleOptions = MapStyleOptions(styleJson)))
    {
        initialPOI.forEach()
        { poi ->
            Marker(title = poi.name,
                state = MarkerState(
                    position = LatLng(
                        poi.location.latitude,
                        poi.location.longitude
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