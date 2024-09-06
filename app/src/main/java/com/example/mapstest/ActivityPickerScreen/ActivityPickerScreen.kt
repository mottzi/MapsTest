package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Abstract.RequestLocationPermission
import com.example.mapstest.Managers.CategoryMapManager

@Composable
fun ActivityPickerScreen()
{
    // create coupled view models
    val categoryMapManager: CategoryMapManager = viewModel()

    // requests location permissions
    RequestLocationPermission(categoryMapManager.mapManager)

    Box(modifier = Modifier.fillMaxSize())
    {
        CategoryMap(mapManager = categoryMapManager.mapManager)
        CategoryPicker(categoryManager = categoryMapManager.categoryManager)
    }
}

