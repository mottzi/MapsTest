package com.example.mapstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mapstest.activityPickerScreen.ActivityPickerScreen
import com.example.mapstest.ui.theme.MapsTestTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent()
        {
            MapsTestTheme()
            {
                // first view to be displayed
                ActivityPickerScreen()
            }
        }
    }
}

