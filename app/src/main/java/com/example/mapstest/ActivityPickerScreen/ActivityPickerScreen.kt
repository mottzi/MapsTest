package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Managers.CategoryManager
import com.example.mapstest.Managers.MapManager

@Composable
fun ActivityPickerScreen(){
    val categoryManager: CategoryManager = viewModel()
    val mapManager: MapManager = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        CategoryMap(mapManager = mapManager)
        CategoryPicker(categoryManager = categoryManager)
    }
}