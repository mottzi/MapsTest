package com.example.mapstest.utils

import com.example.mapstest.models.OSMTagFilter
import com.google.android.gms.maps.model.LatLngBounds

// this class is used by OSMRequest to build valid OSM OverPass API queries
class OSMQuery
{
    // companion object is the same as Swift static class functions
    // so no instance of OSMQuery is needed to call the function, use 'OSMQuery.buildQuery(...)'
    companion object
    {
        // builds a OSM OverPass API query that will returns all POI that match at least one of the provided OSMTagFilter
        // region parameter is provided by camaraPositionState of GoogleMap()
        @Suppress("LiftReturnOrAssignment")
        fun buildQuery(tagFilters: List<OSMTagFilter>, region: LatLngBounds): String?
        {
            // if no tag filter is provided, abort
            if (tagFilters.isEmpty()) return null

            // query settings: JSON output and bbox definition
            var query = "[out:json][bbox:${region.southwest.latitude}, ${region.southwest.longitude}, ${region.northeast.latitude}, ${region.northeast.longitude}];("

            // loop through every tag filter and add the appropriate filter-query
            for (tagFilter in tagFilters)
            {
                // tag-value-pair ["amenity"="cinema"]
                if (tagFilter.value != null)
                {
                    query += "nwr[\"name\"][\"${tagFilter.tag}\"=\"${tagFilter.value}\"];"
                }
                // standalone tag ["sport"]
                else
                {
                    query += "nwr[\"name\"][\"${tagFilter.tag}\"];"
                }
            }

            // ensures that way elements also have coordinates like node elements
            query += ");out center;"

            return query
        }
    }
}