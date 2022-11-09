package com.example.stationareayouthhousing.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stationareayouthhousing.model.dto.Plan

@Dao
interface LocalService {
    @Query("SELECT * FROM `Plan`")
    fun getAllPlan(): List<Plan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlan(plan: Plan)
}