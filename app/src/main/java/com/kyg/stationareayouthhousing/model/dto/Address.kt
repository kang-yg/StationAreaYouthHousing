package com.kyg.stationareayouthhousing.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val borough: String,
    val dong: String,
    val houseNumber: String
) : Parcelable