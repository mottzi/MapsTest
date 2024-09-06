package com.example.mapstest.Managers

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.Abstract.OSMRequest
import com.example.mapstest.Models.OSMCategory
import com.example.mapstest.Models.OSMPointOfInterest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun toggleMapMarkers(category: OSMCategory)
    {
        val region = cameraPosition.projection?.visibleRegion?.latLngBounds ?: return

        if (category.isSelected)
        {
            addMapMarkersOSM(category, region)
        }
        else
        {
            removeMapMarkers(category)
        }
    }

    private fun addMapMarkersOSM(category: OSMCategory, region: LatLngBounds)
    {
        if (category.tagFilters.isEmpty()) return

        val request = OSMRequest(category, region)

        // launch coroutine
        CoroutineScope(Dispatchers.IO).launch()
        {
            val foundItems = request.start() ?: return@launch

            // update UI on the main thread
            withContext(Dispatchers.Main)
            {
                osmSearchResults += foundItems
            }
        }
    }

    private fun removeMapMarkers(category: OSMCategory)
    {
        // Update the state with the filtered list
        osmSearchResults = osmSearchResults.filter { it.category.id != category.id }
    }
}