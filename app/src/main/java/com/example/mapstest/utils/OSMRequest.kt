package com.example.mapstest.utils

import com.example.mapstest.models.OSMCategory
import com.example.mapstest.models.OSMPointOfInterest
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URLEncoder

// make POI requests to OSM Overpass API
// you provide the OSMCategory, OSMRequest builds and runs the query using all tag filters and the map region
class OSMRequest(private val category: OSMCategory, private val region: LatLngBounds)
{
    private val client = OkHttpClient()

    // starts the request asynchronously
    suspend fun start(): List<OSMPointOfInterest>?
    {
        // make sure we have at least one tag filter
        if (category.tagFilters.isEmpty()) return null

        // prepare query string using OSMQuery
        val rawQuery = OSMQuery.buildQuery(category.tagFilters, region) ?: return null
        val query = encodeQuery(rawQuery)
        val urlQuery = "https://overpass-api.de/api/interpreter?data=$query"

        // prepare GET API request
        val request = Request.Builder()
            .url(urlQuery)
            .get()
            .build()

        // perform async API request
        return withContext(Dispatchers.IO)
        {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return@withContext null

            // try to access elements array
            val data = response.body?.string() ?: return@withContext null
            val json = JSONObject(data)
            val elements = json.optJSONArray("elements") ?: return@withContext null

            // parse elements into a list of OSMPointOfInterest
            val parsedPoints = mutableListOf<OSMPointOfInterest>()

            // loop over all elements
            for (i in 0 until elements.length())
            {
                // try to access name tag
                val element = elements.getJSONObject(i)
                val tags = element.optJSONObject("tags") ?: continue
                val name = tags.optString("name") ?: continue

                // save coordinate for node type
                val coordinate = if (element.has("lat") && element.has("lon"))
                {
                    LatLng(
                        element.getDouble("lat"),
                        element.getDouble("lon")
                    )
                }
                // save center-coordinate for way type
                else if (element.has("center"))
                {
                    val center = element.getJSONObject("center")

                    LatLng(
                        center.getDouble("lat"),
                        center.getDouble("lon")
                    )
                }
                else null

                // skip element if no valid coordinate was found
                if (coordinate == null) continue

                // add element to return list
                val mapItem = OSMPointOfInterest(name = name, coordinate = coordinate, category = category)
                parsedPoints.add(mapItem)
            }

            // return parsed JSON elements if at least one element was found
            parsedPoints.ifEmpty { null }
        }
    }
}

// encode raw query to url query (thread safe)
private suspend fun encodeQuery(rawQuery: String): String
{
    return withContext(Dispatchers.IO) { URLEncoder.encode(rawQuery, "UTF-8") }
}