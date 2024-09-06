package com.example.mapstest.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.models.OSMCategory

// manages the state of CategoryPicker
// interacts with MapManager when user toggles a category button
class CategoryManager : ViewModel()
{
    // reference to MapManager so we can call mapManager.toggleMapMarkers() when a category button is toggled
    var mapManager: MapManager? = null

    // list of categories as state variable, this is that CategoryPicker renders
    var allCategories: List<OSMCategory> by mutableStateOf(com.example.mapstest.models.allCategories)

    // toggles the isSelected property and fetches or removes POI from OSM
    fun toggleCategory(category: OSMCategory)
    {
        // update allCategories state variable
        allCategories = allCategories
            // toggle isSelected of tapped category in state variable
            .map { if (it.id == category.id) it.copy(isSelected = !it.isSelected) else it }
            // sort by isSelected property
            .sortedByDescending { it.isSelected }

        // toggle isSelected of local scope category
        category.isSelected = !category.isSelected

        // fetch or remove POI from OSM
        mapManager?.toggleMapMarkers(category)
    }
}