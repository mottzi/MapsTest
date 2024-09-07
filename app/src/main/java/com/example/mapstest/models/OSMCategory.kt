package com.example.mapstest.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Attractions
import androidx.compose.material.icons.outlined.CrueltyFree
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.SportsVolleyball
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

// represents a selectable OSM category that is made up of one or multiple OSMTagFilter
data class OSMCategory(
    val id: UUID = UUID.randomUUID(),

    // used by CategoryPicker
    val title: String,
    val icon: ImageVector,
    var isSelected: Boolean = false,

    // used by OSMQuery and OSMRequest
    val tagFilters: List<OSMTagFilter>
)

// list of OSMCategory that is used by CategoryPicker
val allCategories: List<OSMCategory> = listOf(
    OSMCategory(
        title = "Movies",
        icon = Icons.Outlined.Movie,
        tagFilters = listOf(OSMTagFilter("amenity", "cinema"))
    ),
    OSMCategory(
        title = "Park",
        icon = Icons.Outlined.Park,
        tagFilters = listOf(OSMTagFilter("leisure", "park"))
    ),
    OSMCategory(
        title = "Eat",
        icon = Icons.Outlined.Restaurant,
        tagFilters = listOf(
            OSMTagFilter("amenity", "restaurant"),
            OSMTagFilter("amenity", "fast_food"),
            OSMTagFilter("amenity", "cafe"),
            OSMTagFilter("shop", "bakery"),
            OSMTagFilter("shop", "pastry")
        )
    ),
    OSMCategory(
        title = "Sport",
        icon = Icons.Outlined.SportsVolleyball,
        tagFilters = listOf(
            OSMTagFilter("sport"),
            OSMTagFilter("leisure", "pitch")
        )
    ),
    OSMCategory(
        title = "Museum",
        icon = Icons.Outlined.AccountBalance,
        tagFilters = listOf(
            OSMTagFilter("tourism", "museum"),
            OSMTagFilter("museum")
        )
    ),
    OSMCategory(
        title = "Zoo",
        icon = Icons.Outlined.CrueltyFree,
        tagFilters = listOf(
            OSMTagFilter("tourism", "zoo"),
            OSMTagFilter("zoo")
        )
    ),
    OSMCategory(
        title = "Amusement",
        icon = Icons.Outlined.Attractions,
        tagFilters = listOf(
            OSMTagFilter("attraction", "amusement_ride"),
            OSMTagFilter("leisure", "amusement_arcade"),
            OSMTagFilter("leisure", "water_park"),
            OSMTagFilter("tourism", "theme_park")
        )
    )
)