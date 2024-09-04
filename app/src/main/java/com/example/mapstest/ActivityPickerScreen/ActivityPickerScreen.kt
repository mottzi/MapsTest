package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Abstract.RequiresPermission
import com.example.mapstest.Managers.CategoryManager
import com.example.mapstest.Managers.MapManager

private val locationPermissions = arrayOf(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
)

@Composable
fun ActivityPickerScreen()
{
    // create the view models
    val mapManager: MapManager = viewModel()
    val categoryManager: CategoryManager = viewModel()

    // this wrapper view requests location permissions if not already granted
    RequiresPermission(locationPermissions)
    { permissionsGranted ->
        Box(modifier = Modifier.fillMaxSize())
        {
            CategoryMap(mapManager = mapManager)
            CategoryPicker(categoryManager = categoryManager)

            // only for debugging
            Text(
                text = "Permissions: ${if (permissionsGranted) "granted" else "denied"}",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            )
        }
    }
}