package com.kyg.stationareayouthhousing.model.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Plan(
    val year: String,
    @PrimaryKey val serialNumber: Int,
    val address: Address,
    val station: String,
    val supply: Supply,
    val publicDueDate: String,
    val privateDueDate: String,
    val expectedMoveInDate: String
) : Parcelable
