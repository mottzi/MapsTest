package com.example.mapstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mapstest.ui.theme.MapsTestTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.font.FontWeight
import com.google.maps.android.compose.GoogleMap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapstest.Managers.CategoryManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            MapsTestTheme{
                ActivityPickerScreen()
            }
        }
    }
}

@Composable
fun ActivityPickerScreen(){
    val categoryManager: CategoryManager = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        GoogleMap()
        {

        }
        CategoryPicker(categoryManager = categoryManager)
    }
}

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