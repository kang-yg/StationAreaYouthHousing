package com.example.stationareayouthhousing.model

import com.example.stationareayouthhousing.model.dto.Plan
import kotlinx.coroutines.flow.Flow

interface LocalServiceHelper {
    fun insertPlan(plan: Plan)
    fun getAllPlan(): Flow<List<Plan>>
}