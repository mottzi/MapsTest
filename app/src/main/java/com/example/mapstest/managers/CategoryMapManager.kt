package com.example.mapstest.managers

import androidx.lifecycle.ViewModel

class CategoryMapManager : ViewModel()
{
    val categoryManager = CategoryManager()
    val mapManager = MapManager()

    init
    {
        categoryManager.mapManager = mapManager
        mapManager.categoryManager = categoryManager
    }
}