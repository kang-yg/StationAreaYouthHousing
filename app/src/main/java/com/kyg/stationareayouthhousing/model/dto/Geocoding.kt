package com.kyg.stationareayouthhousing.model.dto

import com.google.gson.annotations.SerializedName

data class Geocoding(
    @SerializedName("addresses") val geocodingAddress: List<GeocodingAddress>
)
