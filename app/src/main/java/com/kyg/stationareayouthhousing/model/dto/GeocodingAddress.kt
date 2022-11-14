package com.kyg.stationareayouthhousing.model.dto

data class GeocodingAddress(
    val roadAddress: String,
    val jibunAddress: String,
    val englishAddress: String,
    val x: Double,
    val y: Double
)
