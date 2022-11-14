package com.kyg.stationareayouthhousing.model

import com.kyg.stationareayouthhousing.model.dto.Geocoding
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/map-geocode/v2/geocode")
    suspend fun getGeocoding(@Query(value = "query", encoded = true) addressStr: String): Response<Geocoding>
}