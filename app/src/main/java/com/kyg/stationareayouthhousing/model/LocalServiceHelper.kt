package com.kyg.stationareayouthhousing.model

import com.kyg.stationareayouthhousing.model.dto.Plan

interface LocalServiceHelper {
    fun getAllPlan(): List<Plan>
    fun insertPlan(plan: Plan)
    fun deleteAllPlan()
}