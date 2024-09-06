package com.example.mapstest.Models

import java.util.UUID

data class OSMCategory(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    var isSelected: Boolean = false,
    val tagFilters: List<OSMTagFilter>
)

val initialCategories: List<OSMCategory> = listOf(
    OSMCategory(
        title = "Movies",
        tagFilters = listOf(
            OSMTagFilter("amenity", "cinema")
        )
    ),
    OSMCategory(
        title = "Park",
        tagFilters = listOf(
            OSMTagFilter("leisure", "park")
        )
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