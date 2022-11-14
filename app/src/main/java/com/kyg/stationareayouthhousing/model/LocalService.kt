package com.kyg.stationareayouthhousing.model

import androidx.room.*
import com.kyg.stationareayouthhousing.model.dto.Plan

@Dao
interface LocalService {
    @Query("SELECT * FROM `Plan`")
    fun getAllPlan(): List<Plan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlan(plan: Plan)

    @Query("DELETE FROM `Plan`")
    fun deleteAllPlan()
}