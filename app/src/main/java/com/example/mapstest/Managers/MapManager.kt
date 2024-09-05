package com.example.mapstest.Managers

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.Abstract.MapCategory
import com.example.mapstest.Abstract.OSMRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

data class OSMPointOfInterest(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val coordinate: LatLng,
    val category: MapCategory
)

class MapManager: ViewModel()
{
    var categoryManager: CategoryManager? = null

    var cameraPosition by mutableStateOf(CameraPositionState())
    var osmSearchResults: List<OSMPointOfInterest> by mutableStateOf(listOf())

    private fun updateCameraPosition(location: LatLng)
    {
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(15f)
            .build()

        this.cameraPosition = CameraPositionState(cameraPosition)
    }

    @SuppressLint("MissingPermission")
    fun centerMapOnUser(context: Context)
    {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener()
        {
            if (it != null) this.updateCameraPosition(LatLng(it.latitude, it.longitude))
        }
    }

    fun addMapMarkersOSM(category: MapCategory)
    {
        println("${category.title} was tapped")

        if (category.osmCategories == null) return
        val region = cameraPosition.projection?.visibleRegion?.latLngBounds ?: return

        val request = OSMRequest(category, region)

        // Launch coroutine on the IO dispatcher for the network request
        CoroutineScope(Dispatchers.IO).launch()
        {
            val foundItems = request.start() ?: return@launch

            // Update the UI on the main thread
            withContext(Dispatchers.Main)
            {
                osmSearchResults += foundItems
            }
        }
    }
}