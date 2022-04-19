package com.shahinbasahr.apollo.network


import com.apollographql.apollo.api.CustomTypeAdapter
import com.apollographql.apollo.api.CustomTypeValue
import org.json.JSONArray
import org.json.JSONObject


class GeoPointScalarAdapter : CustomTypeAdapter<GeoPoint> {

    override fun encode(value: GeoPoint): CustomTypeValue<*> {
        val maps = LinkedHashMap<String, Any>()
        maps["type"] = "Point"
        maps["coordinates"] = listOf(value.lng, value.lat)
        return CustomTypeValue.GraphQLJsonObject(maps)
    }

    override fun decode(value: CustomTypeValue<*>): GeoPoint {
        val geoJson = JSONObject(value.value.toString())
        val geoPositions = geoJson.get("coordinates") as? JSONArray

        val lng : Double = geoPositions?.get(0) as? Double ?: 0.0
        val lat : Double = geoPositions?.get(1) as? Double ?: 0.0
        return GeoPoint(lat, lng)
    }
}

