package com.example.mapstest.Abstract

import java.util.UUID

data class MapCategory(
    val id: UUID = UUID.randomUUID(),

    val title: String,
    val value: String? = null,
    val osmCategories: List<OSMPointOfInterestCategory>? = null,

    var isSelected: Boolean = false
)

data class OSMPointOfInterestCategory(
    val name: String,
    val value: String? = null
)

val initialCategories: List<MapCategory> = listOf(
    MapCategory(
        title = "Movies",
        osmCategories = listOf(
            OSMPointOfInterestCategory("amenity", "cinema")
        )
    ),
    MapCategory(
        title = "Park",
        osmCategories = listOf(
            OSMPointOfInterestCategory("leisure", "park")
        )
    ),
    MapCategory(
        title = "Eat",
        osmCategories = listOf(
            OSMPointOfInterestCategory("amenity", "restaurant"),
            OSMPointOfInterestCategory("amenity", "fast_food"),
            OSMPointOfInterestCategory("amenity", "cafe"),
            OSMPointOfInterestCategory("shop", "bakery"),
            OSMPointOfInterestCategory("shop", "pastry")
        )
    ),
    MapCategory(
        title = "Sport",
        osmCategories = listOf(
            OSMPointOfInterestCategory("sport"),
            OSMPointOfInterestCategory("leisure", "pitch")
        )
    ),
    MapCategory(
        title = "Museum",
        osmCategories = listOf(
            OSMPointOfInterestCategory("tourism", "museum"),
            OSMPointOfInterestCategory("museum")
        )
    ),
    MapCategory(
        title = "Zoo",
        osmCategories = listOf(
            OSMPointOfInterestCategory("tourism", "zoo"),
            OSMPointOfInterestCategory("zoo")
        )
    ),
    MapCategory(
        title = "Amusement",
        osmCategories = listOf(
            OSMPointOfInterestCategory("attraction", "amusement_ride"),
            OSMPointOfInterestCategory("leisure", "amusement_arcade"),
            OSMPointOfInterestCategory("leisure", "water_park"),
            OSMPointOfInterestCategory("tourism", "theme_park")
        )
    )
)