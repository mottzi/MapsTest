package com.example.mapstest.Abstract

import com.google.android.gms.maps.model.LatLngBounds

class OSMQuery
{
    companion object
    {
        fun buildQuery(categoryFilters: List<OSMPointOfInterestCategory>, region: LatLngBounds): String?
        {
            // Abort if empty category
            if (categoryFilters.isEmpty()) return null

            // Get JSON that contains only elements inside bbox
            var query = "[out:json][bbox:${region.southwest.latitude}, ${region.southwest.longitude}, ${region.northeast.latitude}, ${region.northeast.longitude}];("

            // Loop through category filters to build the query
            for (category in categoryFilters)
            {
                // If the category has a value (e.g., "amenity" = "cinema")
                if (category.value != null)
                {
                    query += "nwr[\"name\"][\"${category.name}\"=\"${category.value}\"];"
                }
                // If the category is a standalone tag (e.g., "sport")
                else
                {
                    query += "nwr[\"name\"][\"${category.name}\"];"
                }
            }

            // Add 'center' to ensure way elements have coordinates
            query += ");out center;"

            return query
        }
    }
}