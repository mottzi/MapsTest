package com.example.mapstest.ActivityPickerScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mapstest.Managers.CategoryManager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryPicker(categoryManager: CategoryManager) {
    LazyRow(
        modifier = Modifier.padding(vertical = 60.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        item { Text(" ") }
        items(
            items = categoryManager.allCategories,
            key = { item -> item.id }
        ) { tag ->
            Surface(
                modifier = Modifier
                    .padding(6.dp)
                    .animateItemPlacement(),
                shape = CircleShape,
                color = if(tag.isSelected) Color.hsl(210f, 1f, 0.7f,0.9f) else Color.White,
                shadowElevation = 4.dp,
                onClick = { categoryManager.toggleCategory(tag) },
            ) {
                Text(
                    tag.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(12.dp, 8.dp)
                )
            }
        }
    }
}