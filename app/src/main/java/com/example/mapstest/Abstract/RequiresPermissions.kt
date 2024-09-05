package com.example.mapstest.Abstract

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.mapstest.Managers.MapManager

private val locationPermissions = arrayOf(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
)

@Composable
fun RequestLocationPermission(mapManager: MapManager)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity

    var permissionsGranted by remember { mutableStateOf(false) }

    if (!permissionsGranted)
    {
        permissionsGranted = checkPermissionsCenterMap(permissionsGranted, mapManager, context)
    }

    // permission result callback
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
    { result ->
        permissionsGranted = result.values.all { it }

        if (permissionsGranted)
        {
            mapManager.centerMapOnUser(context)
        }
    }

    // request permissions if not granted
    LaunchedEffect(permissionsGranted)
    {
        if (!permissionsGranted)
        {
            permissionLauncher.launch(locationPermissions)
        }
    }

    // Re-check permissions when user resumes the app
    DisposableEffect(Unit)
    {
        val observer = LifecycleEventObserver()
        { _, event ->
            if (event == Lifecycle.Event.ON_RESUME)
            {
                permissionsGranted =
                    checkPermissionsCenterMap(permissionsGranted, mapManager, context)
            }
        }

        activity.lifecycle.addObserver(observer)

        this.onDispose()
        {
            activity.lifecycle.removeObserver(observer)
        }
    }
}


fun checkPermissionsCenterMap(oldPermission: Boolean, mapManager: MapManager, context: Context): Boolean
{
    val allPermissionsGranted = locationPermissions.all()
    {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    // if permissions have changed from NO to YES, center map on user
    if (allPermissionsGranted != oldPermission && allPermissionsGranted)
    {
        mapManager.centerMapOnUser(context)
    }

    return allPermissionsGranted
}