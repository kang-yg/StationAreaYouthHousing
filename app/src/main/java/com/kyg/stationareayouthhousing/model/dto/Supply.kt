package com.kyg.stationareayouthhousing.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supply(
    val total: String,
    val publicRental: String,
    val privateRental: String
) : Parcelable
