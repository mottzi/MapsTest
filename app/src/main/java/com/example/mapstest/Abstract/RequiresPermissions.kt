package com.example.mapstest.Abstract

import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun RequiresPermission(permissions: Array<String>)
{
    // needed for checking and requesting permissions
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // state variable to track if permissions are granted
    var permissionsGranted by remember { mutableStateOf(false) }

    // checks all permission states and updates state variable accordingly
    fun checkPermissions()
    {
        permissionsGranted = permissions.all()
        {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    // check permissions on start
    checkPermissions()

    // request permissions if at least one is not granted
    if (!permissionsGranted)
    {
        LaunchedEffect(key1 = permissions)
        {
            ActivityCompat.requestPermissions(context as Activity, permissions, 0)
        }
    }

    // re-check permissions when user resumes the app
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver()
        { _, event ->
            if (event == Lifecycle.Event.ON_RESUME)
            {
                checkPermissions()

                if (!permissionsGranted)
                {
                    ActivityCompat.requestPermissions(context as Activity, permissions, 0)
                }
            }
        }

        val lifecycle = (context as? LifecycleOwner)?.lifecycle
        lifecycle?.addObserver(observer)

        onDispose {
            lifecycle?.removeObserver(observer)
        }
    }

    // show content passing down permission state
    //content(permissionsGranted)
}