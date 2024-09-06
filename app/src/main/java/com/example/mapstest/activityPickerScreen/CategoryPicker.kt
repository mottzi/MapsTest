package com.example.mapstest.activityPickerScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mapstest.managers.CategoryManager

// displays a horizontal list of category buttons to filter POI on CategoryMap
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryPicker(categoryManager: CategoryManager)
{
    LazyRow(modifier = Modifier.padding(vertical = 60.dp), contentPadding = PaddingValues(horizontal = 8.dp))
    {
        // empty element needed for proper animations (not sure why)
        item { Text(" ") }

        // creates a button for each category
        items(items = categoryManager.allCategories, key = { item -> item.id })
        {
            // animate poistion of items when re-ordering
            Box(modifier = Modifier.animateItemPlacement())
            {
                CategoryButton(it, categoryManager)
            }
        }
    }
}
