package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Managers.CategoryManager

@Composable
fun ActivityPickerScreen(){
    val categoryManager: CategoryManager = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        CategoryMap()
        CategoryPicker(categoryManager = categoryManager)
    }
}