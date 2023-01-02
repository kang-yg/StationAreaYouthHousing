package com.kyg.stationareayouthhousing.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kyg.stationareayouthhousing.model.dto.Plan

@Database(entities = [Plan::class], version = 2)
@TypeConverters(MyTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun localService(): LocalService
}