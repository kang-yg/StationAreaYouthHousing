package com.example.stationareayouthhousing.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan(
    val year: String,
    @PrimaryKey val serialNumber: Int,
    val address: Address,
    val station: String,
    val supply: Supply,
    val publicDueDate: String,
    val privateDueDate: String,
    val expectedMoveInDate: String
)
