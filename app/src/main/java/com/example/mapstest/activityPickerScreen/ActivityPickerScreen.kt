package com.example.mapstest.activityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.managers.CategoryMapManager
import com.example.mapstest.utils.RequestPermissions

// this view lets the user search activities on a map by filtering categories
@Composable
fun ActivityPickerScreen()
{
    // this parent manager holds categoryManager and mapManager
    // the parent manager links both managers so one can access the other
    val categoryMapManager: CategoryMapManager = viewModel()

    // requests location permissions
    RequestPermissions(categoryMapManager.mapManager)

    Box(modifier = Modifier.fillMaxSize())
    {
        // displays the map
        CategoryMap(mapManager = categoryMapManager.mapManager)

        // displays the category picker
        CategoryPicker(categoryManager = categoryMapManager.categoryManager)
    }
}

