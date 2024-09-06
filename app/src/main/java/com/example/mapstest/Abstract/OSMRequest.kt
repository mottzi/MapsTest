package com.example.mapstest.Abstract

import com.example.mapstest.Models.OSMCategory
import com.example.mapstest.Models.OSMPointOfInterest
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URLEncoder

class OSMRequest(private val category: OSMCategory, private val region: LatLngBounds)
{
    private val client = OkHttpClient()

    suspend fun start(): List<OSMPointOfInterest>?
    {
        // Ensure categories are available
        val filters = category.tagFilters
        if (filters.isEmpty()) return null

        // Prepare query string using OSMQuery
        val rawQuery = OSMQuery.buildQuery(filters, region) ?: return null
        val query = encodeQuery(rawQuery)
        val urlQuery = "https://overpass-api.de/api/interpreter?data=$query"

        // Prepare GET API request
        val request = Request.Builder()
            .url(urlQuery)
            .get()
            .build()

        // Perform API request asynchronously using coroutines
        return withContext(Dispatchers.IO)
        {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return@withContext null

            // Parse response JSON
            val data = response.body?.string() ?: return@withContext null
            val json = JSONObject(data)
            val elements = json.optJSONArray("elements") ?: return@withContext null

            // Parse elements into OSMPointOfInterest objects
            val parsedPoints = mutableListOf<OSMPointOfInterest>()
            for (i in 0 until elements.length())
            {
                val element = elements.getJSONObject(i)
                val tags = element.optJSONObject("tags") ?: continue
                val name = tags.optString("name") ?: continue

                // Handle coordinates for node and way types
                val coordinate = if (element.has("lat") && element.has("lon"))
                {
                    LatLng(
                        element.getDouble("lat"),
                        element.getDouble("lon")
                    )
                }
                else if (element.has("center"))
                {
                    val center = element.getJSONObject("center")
                    LatLng(
                        center.getDouble("lat"),
                        center.getDouble("lon")
                    )
                }
                else null

                // Ensure we have a valid coordinate
                coordinate?.let {
                    val poi = OSMPointOfInterest(
                        name = name,
                        coordinate = it,
                        category = category
                    )
                    parsedPoints.add(poi)
                }
            }

            // Return parsed points or null if empty
            parsedPoints.ifEmpty { null }
        }
    }
}

private suspend fun encodeQuery(rawQuery: String): String
{
    return withContext(Dispatchers.IO) {
        URLEncoder.encode(rawQuery, "UTF-8")
    }
}