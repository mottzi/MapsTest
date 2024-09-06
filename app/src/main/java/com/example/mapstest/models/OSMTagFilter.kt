package com.example.mapstest.models

// OSMTagFilter("amenity", "cinema")    -> OSMQuery: ["amenity"="cinema"]
// OSMTagFilter("sport")                -> OSMQuery: ["sport"]

// represents a OSM tag and its value
// one or multiple OSMTagFilter define one OSMCategory, which OSM
data class OSMTagFilter(
    // tag name ("sport", "amenity", ...)
    val tag: String,
    // the value is optional (standalone tags are possible)
    val value: String? = null
)