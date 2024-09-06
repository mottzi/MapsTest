package com.example.mapstest.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.models.OSMCategory
import com.example.mapstest.models.initialCategories

//
class CategoryManager : ViewModel()
{
    // reference to MapManager so we can call mapManager.toggleMapMarkers() when a category button is toggled
    var mapManager: MapManager? = null

    // list of categories as state variable, this is that CategoryPicker renders
    var allCategories: List<OSMCategory> by mutableStateOf(initialCategories)

    // toggles a category isSelected property and fetches or removes POI from OSM
    fun toggleCategory(category: OSMCategory)
    {
        // abort if the category button wasn't found
        val index = allCategories.indexOfFirst { it.id == category.id }
        if (index == -1) return

        // toggle local category copy
        category.isSelected = !category.isSelected

        // update state variable with toggled category button
        allCategories = allCategories.toMutableList().apply()
        {
            set(index, category)
        }

        // sort category buttons by isSelected property
        allCategories = allCategories.sortedByDescending { it.isSelected }

        // fetch or remove POI from OSM
        mapManager?.toggleMapMarkers(category)
    }
}