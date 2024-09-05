package com.example.mapstest.Managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mapstest.Abstract.MapCategory
import com.example.mapstest.Abstract.initialCategories

class CategoryManager : ViewModel()
{
    var mapManager: MapManager? = null

    var allCategories: List<MapCategory> by mutableStateOf(initialCategories)

    fun toggleCategory(category: MapCategory)
    {
        // toggle isSelected of category
        allCategories = allCategories.map()
        {
            if (it.id == category.id) it.copy(isSelected = !it.isSelected)
            else it
        }

        // sort the categories by isSelected
        allCategories = allCategories.sortedByDescending { it.isSelected }

        mapManager?.addMapMarkersOSM(category)
    }
}