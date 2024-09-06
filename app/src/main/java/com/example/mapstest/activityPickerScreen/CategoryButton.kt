package com.example.mapstest.activityPickerScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mapstest.managers.CategoryManager
import com.example.mapstest.models.OSMCategory

// selectable category button
@Composable
fun CategoryButton(category: OSMCategory, categoryManager: CategoryManager)
{
    Surface(
        modifier = Modifier.padding(6.dp),
        shape = CircleShape,
        shadowElevation = 4.dp,
        // background color is dependent on whether the category is selected
        color = if (category.isSelected) Color.hsl(210f, 1f, 0.7f, 0.9f) else Color.White,
        // toggle button when clicked and fetch or remove POI on map
        onClick = { categoryManager.toggleCategory(category) }
    )
    {
        Text(
            text = category.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp, 8.dp)
        )
    }
}