package com.example.mapstest.managers

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.models.OSMCategory
import com.example.mapstest.models.OSMPointOfInterest
import com.example.mapstest.utils.OSMRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// this manages the map camera and POI markers
class MapManager: ViewModel()
{
    // reference to CategoryManager: not yet needed
    var categoryManager: CategoryManager? = null

    // this holds the camara position state of the map
    var cameraPosition by mutableStateOf(CameraPositionState())

    // this state variable holds a list of OSMPointOfInterest to be displayed on the map
    var osmSearchResults: List<OSMPointOfInterest> by mutableStateOf(listOf())

    // this function fetches the user's current location and centers the map camera accordingly
    @SuppressLint("MissingPermission")
    fun centerMapOnUser(context: Context)
    {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener()
        {
            if (it != null) this.updateCameraPosition(LatLng(it.latitude, it.longitude))
        }
    }

    // is called from CategoryManager when the user taps a category button in CategoryPicker
    fun toggleMapMarkers(category: OSMCategory)
    {
        val region = cameraPosition.projection?.visibleRegion?.latLngBounds ?: return

        // if category is selected, add markers to the map
        if (category.isSelected)
        {
            addMapMarkersOSM(category, region)
        }
        // if category is not selected, remove markers from the map
        else
        {
            removeMapMarkers(category)
        }
    }

    // requests all OSM POI that match the given category and coordinate region and adds them to the map
    private fun addMapMarkersOSM(category: OSMCategory, region: LatLngBounds)
    {
        if (category.tagFilters.isEmpty()) return

        // prepare the request
        val request = OSMRequest(category, region)

        // start coroutine
        CoroutineScope(Dispatchers.IO).launch()
        {
            // perform async request and get results
            val foundItems = request.start() ?: return@launch

            // update UI on the main thread
            withContext(Dispatchers.Main)
            {
                osmSearchResults += foundItems
            }
        }
    }

    // removes all POI of the given category from the map
    private fun removeMapMarkers(category: OSMCategory)
    {
        // update the state with the filtered list
        osmSearchResults = osmSearchResults.filter { it.category.id != category.id }
    }

    // this is private to make sure centerMapOnUser() is used instead of directly modifying cameraPosition
    private fun updateCameraPosition(location: LatLng)
    {
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(15f)
            .build()

        this.cameraPosition = CameraPositionState(cameraPosition)
    }
}