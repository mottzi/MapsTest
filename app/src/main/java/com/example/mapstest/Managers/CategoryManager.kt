package com.example.mapstest.Managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.Models.OSMCategory
import com.example.mapstest.Models.initialCategories

@Suppress("NAME_SHADOWING")
class CategoryManager : ViewModel()
{
    var mapManager: MapManager? = null

    var allCategories: List<OSMCategory> by mutableStateOf(initialCategories)

    fun toggleCategory(category: OSMCategory)
    {
        val index = allCategories.indexOfFirst { it.id == category.id }
        if (index == -1) return

        val category = allCategories[index].copy(isSelected = !allCategories[index].isSelected)

        allCategories = allCategories.toMutableList().apply()
        {
            set(index, category)
        }

        allCategories = allCategories.sortedByDescending { it.isSelected }

        mapManager?.toggleMapMarkers(category)
    }
}