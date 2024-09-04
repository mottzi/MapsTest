package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Abstract.RequestLocationPermission
import com.example.mapstest.Managers.CategoryManager
import com.example.mapstest.Managers.MapManager

@Composable
fun ActivityPickerScreen()
{
    // create the view models
    val mapManager: MapManager = viewModel()
    val categoryManager: CategoryManager = viewModel()

    // requests location permissions if not already granted
    RequestLocationPermission(mapManager)

    Box(modifier = Modifier.fillMaxSize())
    {
        CategoryMap(mapManager = mapManager)
        CategoryPicker(categoryManager = categoryManager)
    }
}