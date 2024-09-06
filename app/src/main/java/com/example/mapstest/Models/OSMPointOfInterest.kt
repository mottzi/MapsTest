package com.example.mapstest.Models

import com.google.android.gms.maps.model.LatLng
import java.util.UUID

// represents a POI from OSM
data class OSMPointOfInterest(
    val id: UUID = UUID.randomUUID(),

    // POI properties that get fetched using OSMRequest
    val name: String,
    val coordinate: LatLng,

    // category that was used to fetch this POI so we can remove it again when unselecting the category
    val category: OSMCategory
)