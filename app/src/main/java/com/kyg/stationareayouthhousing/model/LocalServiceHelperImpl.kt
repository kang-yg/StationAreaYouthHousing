package com.kyg.stationareayouthhousing.model

import com.kyg.stationareayouthhousing.model.dto.Plan
import javax.inject.Inject

class LocalServiceHelperImpl @Inject constructor(private val appDataBase: AppDataBase) : LocalServiceHelper {
    override fun getAllPlan(): List<Plan> = appDataBase.localService().getAllPlan()

    override fun insertPlan(plan: Plan) = appDataBase.localService().insertPlan(plan)
}