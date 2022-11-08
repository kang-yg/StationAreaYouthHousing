package com.example.stationareayouthhousing.model

import com.example.stationareayouthhousing.model.dto.Plan
import javax.inject.Inject

class Repository @Inject constructor(private val localServiceHelper: LocalServiceHelper) {
    fun getAllPlan() = localServiceHelper.getAllPlan()
    fun insertPlan(plan: Plan) = localServiceHelper.insertPlan(plan)
}