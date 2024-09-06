package com.example.mapstest.Models

import java.util.UUID

// represents a selectable OSM category that is made up of one or multiple OSMTagFilter
data class OSMCategory(
    val id: UUID = UUID.randomUUID(),

    // used by CategoryPicker
    val title: String,
    var isSelected: Boolean = false,

    // used by OSMQuery and OSMRequest
    val tagFilters: List<OSMTagFilter>
)

// list of OSMCategory that is used by CategoryPicker
val initialCategories: List<OSMCategory> = listOf(
    OSMCategory(
        title = "Movies",
        tagFilters = listOf(OSMTagFilter("amenity", "cinema"))
    ),
    OSMCategory(
        title = "Park",
        tagFilters = listOf(OSMTagFilter("leisure", "park"))
    ),
    OSMCategory(
        title = "Eat",
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
        tagFilters = listOf(
            OSMTagFilter("sport"),
            OSMTagFilter("leisure", "pitch")
        )
    ),
    OSMCategory(
        title = "Museum",
        tagFilters = listOf(
            OSMTagFilter("tourism", "museum"),
            OSMTagFilter("museum")
        )
    ),
    OSMCategory(
        title = "Zoo",
        tagFilters = listOf(
            OSMTagFilter("tourism", "zoo"),
            OSMTagFilter("zoo")
        )
    ),
    OSMCategory(
        title = "Amusement",
        tagFilters = listOf(
            OSMTagFilter("attraction", "amusement_ride"),
            OSMTagFilter("leisure", "amusement_arcade"),
            OSMTagFilter("leisure", "water_park"),
            OSMTagFilter("tourism", "theme_park")
        )
    )
)