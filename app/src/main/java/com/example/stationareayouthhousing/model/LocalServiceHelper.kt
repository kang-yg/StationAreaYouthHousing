package com.example.stationareayouthhousing.model

import com.example.stationareayouthhousing.model.dto.Plan

interface LocalServiceHelper {
    fun getAllPlan(): List<Plan>
    fun insertPlan(plan: Plan)
}