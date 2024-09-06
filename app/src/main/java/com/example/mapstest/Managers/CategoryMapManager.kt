package com.example.mapstest.Managers

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