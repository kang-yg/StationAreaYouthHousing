package com.kyg.stationareayouthhousing.model

import com.kyg.stationareayouthhousing.model.dto.Geocoding
import retrofit2.Response

interface RemoteServiceHelper {
    suspend fun getGeocoding(addressStr: String): Response<Geocoding>
}