package com.kyg.stationareayouthhousing.model

import com.kyg.stationareayouthhousing.model.dto.Geocoding
import retrofit2.Response
import javax.inject.Inject

class RemoteServiceHelperImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteServiceHelper {
    override suspend fun getGeocoding(addressStr: String): Response<Geocoding> = remoteService.getGeocoding(addressStr)
}