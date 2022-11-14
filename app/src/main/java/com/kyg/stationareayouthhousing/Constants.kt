package com.kyg.stationareayouthhousing

import com.kyg.stationareayouthhousing.model.dto.Address

object Constants {
    const val BASE_URL = "https://naveropenapi.apigw.ntruss.com"

    fun Address.concatAddress(): String = StringBuilder().apply {
        this@apply.append(borough.plus(" "))
        this@apply.append(dong.plus(" "))
        this@apply.append(houseNumber)
    }.toString()
}