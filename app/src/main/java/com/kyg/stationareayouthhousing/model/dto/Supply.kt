package com.kyg.stationareayouthhousing.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supply(
    val total: Int,
    val publicRental: Int,
    val privateRental: Int
) : Parcelable
