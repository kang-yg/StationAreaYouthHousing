package com.example.stationareayouthhousing.model

import com.example.stationareayouthhousing.model.dto.Plan
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalServiceHelperImpl @Inject constructor(private val appDataBase: AppDataBase) : LocalServiceHelper {
    override fun getAllPlan(): Flow<List<Plan>> = appDataBase.localService().getAllPlan()

    override fun insertPlan(plan: Plan) = appDataBase.localService().insertPlan(plan)
}