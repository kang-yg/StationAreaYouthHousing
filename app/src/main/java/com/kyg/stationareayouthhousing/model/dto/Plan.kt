package com.kyg.stationareayouthhousing.model.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Room Version 1
 */
/*
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
 */

/**
 * Room Version 2
 */
@Entity(tableName = "Plan")
@Parcelize
data class Plan(
    val year: String,
    val complexName: String,
    @PrimaryKey val address: Address,
    val station: String,
    val supply: Supply,
    val publicDueDate: String,
    val privateDueDate: String,
) : Parcelable
