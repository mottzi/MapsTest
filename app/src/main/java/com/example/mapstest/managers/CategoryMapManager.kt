package com.example.mapstest.managers

import androidx.lifecycle.ViewModel

// this parent manager holds CategoryManager and MapManager
// each manager has a reference to the other manager for interactions between them
class CategoryMapManager : ViewModel()
{
    // the view models
    val categoryManager = CategoryManager()
    val mapManager = MapManager()

    init
    {
        // this couples the managers together
        categoryManager.mapManager = mapManager
        mapManager.categoryManager = categoryManager
    }
}