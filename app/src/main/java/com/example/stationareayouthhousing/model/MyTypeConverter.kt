package com.example.stationareayouthhousing.model

import androidx.room.TypeConverter
import com.example.stationareayouthhousing.model.dto.Address
import com.example.stationareayouthhousing.model.dto.Supply
import com.google.gson.Gson

class MyTypeConverter {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(address: String): Address {
        return Gson().fromJson(address, Address::class.java)
    }

    @TypeConverter
    fun fromSupply(supply: Supply): String {
        return Gson().toJson(supply)
    }

    @TypeConverter
    fun toSupply(supply: String): Supply {
        return Gson().fromJson(supply, Supply::class.java)
    }
}